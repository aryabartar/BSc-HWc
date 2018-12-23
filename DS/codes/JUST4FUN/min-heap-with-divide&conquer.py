def split_array(A):
    A1 = [0]
    A2 = [0]

    p1 = 2
    j = 1

    while True:
        try:
            for i in range(0, j):
                A1.append(A[p1 + i])
            p1 *= 2
            j *= 2

        except:
            break

    p2 = 3
    j = 1

    while True:
        try:
            for i in range(0, j):
                A2.append(A[p2 + i])
            p2 *= 2
            j *= 2

        except:
            break

    return A1, A2


# def min_heap(A):
#     if len(A) < 2:
#         return False
#
#     A1, A2 = split_array(A)
#     print(A1)


print(split_array([0, 1, 4, 7, 5, 8, 8, 11, 22, 33, 44, 55, 0]))
