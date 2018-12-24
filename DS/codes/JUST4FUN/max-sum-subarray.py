# function to return maximum number among three numbers
def maximum(a, b, c):
    if (a >= b and a >= c):
        return a
    elif (b >= a and b >= c):
        return b
    return c


# function to find maximum sum of subarray crossing the middle element
def max_crossing_subarray(ar, low, mid, high):
    '''
      Initial left_sum should be -infinity.
    '''
    left_sum = -10000
    sum = 0

    '''
      iterating from middle
      element to the lowest element
      to find the maximum sum of the left
      subarray containing the middle
      element also.
    '''
    for i in range(mid, low - 1, -1):
        sum = sum + ar[i]
        if (sum > left_sum):
            left_sum = sum

    '''
      Similarly, finding the maximum
      sum of right subarray containing
      the adjacent right element to the
      middle element.
    '''
    right_sum = -10000
    sum = 0

    for i in range(mid + 1, high + 1):
        sum = sum + ar[i]
        if (sum > right_sum):
            right_sum = sum

    '''
      returning the maximum sum of the subarray
      containing the middle element.
    '''
    return left_sum + right_sum


# function to calculate the maximum subarray sum
def max_sum_subarray(ar, low, high):
    if (high == low):  # only one element in an array
        return ar[high]

    # middle element of the array
    mid = (high + low) // 2

    # maximum sum in the left subarray
    maximum_sum_left_subarray = max_sum_subarray(ar, low, mid)
    # maximum sum in the right subarray
    maximum_sum_right_subarray = max_sum_subarray(ar, mid + 1 , high)
    # maximum sum in the array containing the middle element
    maximum_sum_crossing_subarray = max_crossing_subarray(ar, low, mid, high)

    # returning the maximum among the above three numbers
    return maximum(maximum_sum_left_subarray, maximum_sum_right_subarray, maximum_sum_crossing_subarray)


a = [5, -2, 4, 10, -8, 2, -7]
print(max_sum_subarray(a, 0, 6))
