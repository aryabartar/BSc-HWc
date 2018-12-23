def split_array(A):
    A1 = [0]
    A2 = [0]

    p1 = 1
    j = 1
    while True:
        try:
            for i in range(0, j):
                A1.append(A[p1 + j + i - 1])
            p1 *= 2
            j *= 2

        except:
            break

    p2 = 2
    j = 1
    while True:
        try:
            for i in range(0, j):
                A2.append(A[p2 + j + i - 1])
            p2 *= 2
            j *= 2

        except:
            break

    return A1, A2


def min_heap(A):
    if len(A) < 2:
        return False

    A1, A2 = split_array(A)
    A1 = min_heap(A1)
    A2 = min_heap(A2)

    merge_arrays(A1, A2)
    min_heapify(A)

    return A


def merge_arrays(A1, A2):
    A = [0, 0]
    j = 1
    p = 1

    while True:
        try:
            for i in range(p, p + j):
                A.append(A1[i])
            for i in range(p, p + j):
                A.append(A2[i])
            p *= 2
            j *= 2
        except:
            break

    return A

