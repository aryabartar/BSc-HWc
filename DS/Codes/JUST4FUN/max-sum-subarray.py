def maximum(a, b, c):
    if (a >= b and a >= c):
        return a
    elif (b >= a and b >= c):
        return b
    return c



def max_crossing_subarray(ar, low, mid, high):
    left_sum = -10000 #big value
    sum = 0

    for i in range(mid, low - 1, -1):
        sum = sum + ar[i]
        if (sum > left_sum):
            left_sum = sum

    right_sum = -10000#big value
    sum = 0

    for i in range(mid + 1, high + 1):
        sum = sum + ar[i]
        if (sum > right_sum):
            right_sum = sum

    return left_sum + right_sum



def max_sum_subarray(ar, low, high):
    if (high == low):  # one element
        return ar[high]


    mid = (high + low) // 2


    maximum_sum_left_subarray = max_sum_subarray(ar, low, mid)
    maximum_sum_right_subarray = max_sum_subarray(ar, mid + 1 , high)

    maximum_sum_crossing_subarray = max_crossing_subarray(ar, low, mid, high)

    return maximum(maximum_sum_left_subarray, maximum_sum_right_subarray, maximum_sum_crossing_subarray)


a = [5, -2, 4, 10, -8, 2, -7]
print(max_sum_subarray(a, 0, 6))
