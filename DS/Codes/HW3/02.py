def compare_strings(str1, str2):
    if str1 > str2:
        return str1
    return str2


def find_min_insertions(str, l, h):
    print("------")
    print(l, " ", h)

    if l > h:
        return ""
    if l == h:
        return str[l]
    if l == h - 1:
        if str[l] == str[h]:
            return str[l:h + 1]
        else:
            print(str[h] + str[l:h + 1], "\n")
            return str[h] + str[l:h + 1]

    if str[l] == str[h]:
        print("kjdifjiejfiejf")
        print(find_min_insertions(str, l + 1, h - 1))
        return str[l] + find_min_insertions(str, l + 1, h - 1) + str[l]
    else:
        str1 = find_min_insertions(str, l, h - 1)
        str2 = find_min_insertions(str, l + 1, h)
        print("str1 is : ", str1)
        print("str2 is : ", str2)

        if len(str1) >= len(str2):
            temp_str = str[h] + str1 + str[h]
            print("1 is chosen : " + temp_str)
            return temp_str

        else:
            temp_str = str[l] + str2 + str[l]
            print("2 is chosen : " + temp_str)
            return temp_str


str = "MADAMIMADAM"
print(find_min_insertions(str, 0, len(str) - 1))
