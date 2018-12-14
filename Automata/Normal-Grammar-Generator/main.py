def read_files():
    text_file = open("test-cases/0.txt", "r").read()
    lines = text_file.split("\n")

    dict = {}
    for line in lines:
        if line[0] not in dict.keys():
            dict[line[0]] = [line[1:]]
        else:
            dict[line[0]] = dict[line[0]] + [line[1:]]
            pass

    return dict


def remove_l_production(dict):
    def manipulate_dictionary(dict):
        for key in dict:
            dict[key] = list(set(dict[key]))
            while 'l' in dict[key]:
                dict[key].remove('l')
            while '' in dict[key]:
                dict[key].remove('')
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
    for key in dict:
        for item in dict[key]:
            if item == "l":
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

    return manipulate_dictionary(dict)


# Maybe has bug in dependency graph
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

    def find_symbols_dont_derive_final(dict):
        symbol_list = []
        for key in dict:
            if has_terminal(dict[key]):
                symbol_list.append(key)

        print(symbol_list)
        for i in range(0, len(dict)):
            for key in dict:
                if key in symbol_list:
                    continue
                else:
                    print("\n")
                    print("key is : " + key)
                    print(dict[key])
                    print("Symbol list is : ", symbol_list)
                    if is_final_variable(dict[key], symbol_list):
                        symbol_list.append(key)
                    print("\n")

        print(symbol_list)

    find_symbols_dont_derive_final(dict)
    return dict


dict = read_files()

# dict = remove_l_production(dict)
# dict = remove_unit_productions(dict)
# dict = remove_unit_productions(dict)
dict = remove_useless_productions(dict)
# print(dict)
