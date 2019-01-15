def find_min_insertions(str, l, h):

    if l > h:
        return 1000000
    if l == h:
        return 0
    if l == h - 1:
        if str[l] == str[h]:
            return 0
        else:
            return 1

    if str[l] == str[h]:
        return find_min_insertions(str, l + 1, h - 1)
    else:
        return (min(find_min_insertions(str, l, h - 1),
                    find_min_insertions(str, l + 1, h)) + 1)


str = "geeks"
print(find_min_insertions(str, 0, len(str) - 1))
