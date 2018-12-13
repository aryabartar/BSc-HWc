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
    v = []
    for key in dict:
        for item in dict[key]:
            if item == "l":
                v.append(key)
                break
    # TODO : should you repeate this multiple times?!
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

    return dict


dict = read_files()
print(dict)
dict = remove_l_production(dict)
