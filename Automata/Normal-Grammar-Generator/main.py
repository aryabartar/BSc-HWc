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


def remove_unit_productions(dict):
    return dict


dict = read_files()
# dict = remove_l_production(dict)
dict = remove_unit_productions(dict)
