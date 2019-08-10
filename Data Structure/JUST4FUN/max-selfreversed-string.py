def can_reverse(A, index, length):
    try:
        if index - length < 0 or index + length > len(A):
            return length

        elif A[index - length] == A[index + length]:
            length += 1
            return can_reverse(A, index, length)

        else:
            return length

    except:
        return length


def find_max_reverse(A):
    index = 0
    max_reverse_len = 0
    
    for i in A:
        reverse_len = can_reverse(A, index, 0)
        index += 1
        if reverse_len > max_reverse_len:
            max_reverse_len = reverse_len

    if max_reverse_len == 0:
        return 0

    return max_reverse_len * 2 - 1


print(find_max_reverse("aakayakbaab"))  # PRINTS 5


