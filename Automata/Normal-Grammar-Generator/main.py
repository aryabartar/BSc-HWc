from os import walk


def read_files(file_address):
    print("reading file : ", file_address)
    text_file = open(file_address, "r").read()
    lines = text_file.split("\n")

    dict = {}
    for line in lines:
        if line[0] not in dict.keys():
            dict[line[0]] = [line[1:]]
        else:
            dict[line[0]] = dict[line[0]] + [line[1:]]
            pass

    print("\ninput is : ")
    show_grammar_output(dict)

    return dict


def get_pdf_list():
    f = []
    for (dirpath, dirnames, filenames) in walk("./test-cases"):
        f.extend(filenames)
        break
    return f


def show_grammar_output(dict):
    for key in dict:
        print(key + "-> ", end='')
        is_first_value = True
        for production_value in dict[key]:
            if is_first_value:
                print(production_value, end='')
                is_first_value = False
            else:
                print(" | " + production_value, end='')

        print('')


def remove_l_production(dict):
    def manipulate_dictionary(dictm,just_has_has_lambda_variables):
        for key in dict:
            dict[key] = list(set(dict[key]))
            while 'l' in dict[key]:
                dict[key].remove('l')
            while '' in dict[key]:
                dict[key].remove('')

        for key in dict :
            for item in dict[key] :
                if item in just_has_has_lambda_variables:
                    dict[key].remove(item)
        return dict

    def make_i_empty(string, i):
        temp = list(string)
        temp[i] = " "
        return "".join(temp)

    def make_permutation(str, indexes):
        permute_list = ['1' * len(str)]
        permute_string = []
        for i in indexes:
            temp_list = []

            for item1 in permute_list:
                temp_list.append(item1)
                temp_list.append(make_i_empty(item1, i))

            permute_list += temp_list
            permute_list = list(set(permute_list))

        for item1 in permute_list:
            temp_str = ""

            for i in range(0, len(item1)):
                if item1[i] == '1':
                    temp_str += str[i]

            permute_string.append(temp_str)

        permute_string = list(set(permute_string))
        return permute_string

    def permute(v, item):
        permute_chars_index = []
        chars = item

        for i in range(0, len(chars)):
            if chars[i] in v:
                permute_chars_index.append(i)
        return make_permutation(chars, permute_chars_index)

    v = []
    just_has_has_lambda_variables = []
    for key in list(dict):
        for item in dict[key]:
            if item == "l":
                if len(dict[key]) == 1:
                    just_has_has_lambda_variables.append(key)
                    del (dict[key])
                v.append(key)
                break

    # TODO : should you repeate this multiple times?!
    for i in range(0, len(dict.keys())):
        for key in dict:
            if key in v:
                continue

            for item in dict[key]:
                is_nullable = True
                for char in item:
                    if char not in v:
                        is_nullable = False

                if is_nullable:
                    v.append(key)
                    break

    for key in dict:
        temp_list = []
        for item in dict[key]:
            item = permute(v, item)
            temp_list += item
        dict[key] = temp_list

    return manipulate_dictionary(dict,just_has_has_lambda_variables)


# TODO: Maybe has bug in dependency graph
def remove_unit_productions(dict):
    def manipulate_dictionary(dict):
        for key in dict:
            dict[key] = list(set(dict[key]))
        return dict

    def find_unit_production_array(production_list):
        temp_list = []
        for item in production_list:
            if item.upper() == item and len(item) == 1:
                temp_list.append(item)
        return temp_list

    def find_recursive_dependency(raw_dict, key, dependency_list):
        if key in dependency_list:
            return True
        dependency_list.append(key)
        for item in raw_dict[key]:
            find_recursive_dependency(raw_dict, item, dependency_list)

    def find_dependencies(dict):
        temp_dict = {}
        for key in dict:
            temp_dict[key] = find_unit_production_array(dict[key])

        for key in temp_dict:
            dependency_list = []
            find_recursive_dependency(temp_dict, key, dependency_list)
            dependency_list.remove(key)
            temp_dict[key] = dependency_list

        return temp_dict

    def find_non_unit_production_array(production_list):
        temp_list = []
        for item in production_list:
            if not (item.upper() == item and len(item) == 1):
                temp_list.append(item)
        return temp_list

    def return_removed_version(dependency_dict, non_unit_production_dict):
        temp_dict = {}
        for key in dependency_dict:
            temp_dict[key] = non_unit_production_dict[key]

        for key in dependency_dict:
            for value in dependency_dict[key]:
                temp_dict[key] += non_unit_production_dict[value]
        return temp_dict

    non_unit_production_dict = {}
    for key in dict:
        non_unit_production_dict[key] = find_non_unit_production_array(dict[key])

    dependency_dict = find_dependencies(dict)

    return manipulate_dictionary(return_removed_version(dependency_dict, non_unit_production_dict))


def remove_useless_productions(dict):
    def delete_non_final_variables(dict, final_array):
        deleted_variables = []
        for key in list(dict):
            if key not in final_array:
                deleted_variables.append(key)
                del dict[key]

        for key in list(dict):
            for_remove_list = []
            for production in dict[key]:
                temp_list = list(production)
                for i in deleted_variables:
                    if i in temp_list:
                        for_remove_list.append(production)
            dict[key] = [x for x in dict[key] if x not in for_remove_list]

        return dict

    def is_final_variable(production_list, symbol_list):

        for item in production_list:
            temp_list = list(item)
            is_all_final = True

            for i in temp_list:
                if (i not in symbol_list) and (i.upper() == i):
                    is_all_final = False

            if is_all_final:
                return True

        return False

    def has_terminal(production_list):
        for item in production_list:
            if item.lower() == item:
                return True
        return False

    def make_ready_for_dependency_graph(dict):
        temp_dict = {}
        for key in dict:
            temp_list = []
            for item in dict[key]:
                for i in list(item):
                    if i.upper() == i:
                        temp_list.append(i)
            temp_dict[key] = list(set(temp_list))

        return temp_dict

    def find_dependency(variable, dict, dependency_list):
        for item in dict[variable]:
            dependency_list.append(item)
            if variable not in dependency_list:
                find_dependency(item, dict, dependency_list)

    def delete_unreachable_variables(dict, dependency_list):
        deleted_list = []
        for key in list(dict):
            if key == 'S':
                continue

            if key not in dependency_list:
                del dict[key]
                deleted_list.append(key)

        for key in list(dict):
            for production in dict[key]:
                temp_list = list(production)
                for i in deleted_list:
                    if i in temp_list:
                        dict[key].remove(production)

        return dict

    def find_symbols_dont_derive_final(dict):
        symbol_list = []
        for key in dict:
            if has_terminal(dict[key]):
                symbol_list.append(key)

        for i in range(0, len(dict)):
            for key in dict:
                if key in symbol_list:
                    continue
                else:
                    if is_final_variable(dict[key], symbol_list):
                        symbol_list.append(key)

        dict = delete_non_final_variables(dict, symbol_list)
        dependency_ready_dict = make_ready_for_dependency_graph(dict)

        s_dependency = []
        find_dependency('S', dependency_ready_dict, s_dependency)
        s_dependency = list(set(s_dependency))

        if 'S' in s_dependency:
            s_dependency.remove('S')

        delete_unreachable_variables(dict, s_dependency)

    find_symbols_dont_derive_final(dict)
    return dict


for i in range(1, 11):
    dict = read_files("test-cases/{number}.txt".format(number=i))

    print("\nAfter removing lambda : ")
    dict = remove_l_production(dict)
    show_grammar_output(dict)

    print("\nAfter removing unit productions : ")
    dict = remove_unit_productions(dict)
    show_grammar_output(dict)

    print("\nAfter removing useless productions : ")
    dict = remove_useless_productions(dict)
    show_grammar_output(dict)

    print("\nFINAL Normal form is : ")
    show_grammar_output(dict)

    print("\n___________\n")
