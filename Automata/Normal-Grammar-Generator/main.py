def read_files():
    text_file = open("test-cases/1.txt", "r").read()
    lines = text_file.split("\n")

    dict = {}
    for line in lines:
        if line[0] not in dict.keys():
            dict[line[0]] = [line[1:]]
        else:
            dict[line[0]] = dict[line[0]] + [line[1:]]
            pass

    return dict


print(read_files())
