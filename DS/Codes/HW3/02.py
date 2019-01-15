def compare_strings(str1, str2):
    if str1 < str2:
        return str1
    return str2


def find_min_insertions(matrix, str, l, h):
    # print("------")
    # print(l, " ", h)

    if l > h:
        return ""
    if l == h:
        return str[l]
    if l == h - 1:
        if matrix[l][h] != 0:
            return matrix[l][h]

        if str[l] == str[h]:
            matrix[l][h] = str[l:h + 1]
            return matrix[l][h]
        else:
            str1 = str[h] + str[l:h + 1]
            str2 = str[l:h + 1] + str[l]
            matrix[l][h] = compare_strings(str1, str2)
            return matrix[l][h]

    if matrix[l][h] != 0:
        return matrix[l][h]

    if str[l] == str[h]:
        # print("OOOOOOPPPPSSS")
        matrix[l][h] = str[l] + find_min_insertions(matrix, str, l + 1, h - 1) + str[l]
        return matrix[l][h]
    else:
        str1 = find_min_insertions(matrix, str, l, h - 1)
        str2 = find_min_insertions(matrix, str, l + 1, h)
        # print("str1 is : ", str1)
        # print("str2 is : ", str2)

        temp_str1 = str[h] + str1 + str[h]
        temp_str2 = str[l] + str2 + str[l]
        if len(str1) < len(str2):
            # print("1 is chosen : " + temp_str1)
            return temp_str1

        elif len(str1) > len(str2):
            # print("2 is chosen : " + temp_str2)
            return temp_str2

        else:
            # print("EQUALITY!!!")
            if temp_str1 == compare_strings(temp_str1, temp_str2):
                # print("2 is chosen : " + temp_str1)
                return temp_str1
            else:
                # print("2 is chosen : " + temp_str2)
                return temp_str2


str = input()
w = len(str)
matrix = [[0 for x in range(w)] for y in range(w)]
print(find_min_insertions(matrix, str, 0, len(str) - 1))
# print(matrix)
# print(compare_strings("A" , "B"))
