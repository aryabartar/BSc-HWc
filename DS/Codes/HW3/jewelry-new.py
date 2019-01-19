import math
from functools import reduce
import operator as op


class Information:
    def __init__(self):
        self.partitions = 0


def get_weights():
    input()  # Nothing :D
    weights = [int(x) for x in input().split(" ")]
    weights.sort()

    return weights


def get_different_combinations(info, n, k):
    if info.comb[n][k] > 0:
        return info.comb[n][k]
    elif k == 0:
        return 1
    elif n == k:
        return 1
    else:
        r = min(k, n - k)
        numer = reduce(op.mul, range(n, n - r, -1), 1)
        denom = reduce(op.mul, range(1, r + 1), 1)
        info.comb[n][k] = numer / denom
        return numer / denom


def ncr(info, n, r):
    r = min(r, n - r)
    numer = reduce(op.mul, range(n, n - r, -1), 1)
    denom = reduce(op.mul, range(1, r + 1), 1)
    return numer / denom


def nCr(n, r):
    if r == 0:
        return 1
    elif n == r:
        return 1

    f = math.factorial

    return int(f(n) / f(r) / f(n - r))


def get_different_sums_p(weights):
    sum = 0
    for item in weights:
        sum += item

    sum_ways = [1] + [0] * (sum + 1)

    for i in range(0, len(weights)):
        j = sum
        while j >= weights[i]:
            sum_ways[j] += sum_ways[j - weights[i]]
            j -= 1

    return sum_ways


def summation(*args):
    s = 0
    for i in args:
        for j in i:
            s += j
    return s


def divide(weights, partitions):
    def update_group_size(gp_size, j, i):
        gp_size = 1
        while j < len(weights) and weights[i] == weights[j]:
            gp_size += 1
            j += 1
        return gp_size

    info = Information()
    # temp_partitions = 0
    i = 0

    while i < len(weights) - 1:
        gp_size = update_group_size(1, i + 1, i)

        asghar = get_different_sums_p(weights[0: i])
        for size in range(0, gp_size):
            weights_size = size + 1
            akbar = get_different_sums_p(weights[i + size + 1: len(weights)])
            temp_partitions = 0

            for s in range(weights[i] * weights_size, 16000):
                try:
                    partitions += int(get_different_combinations(info, gp_size, size + 1)) * \
                                  asghar[s - weights[i] * (size + 1)] * \
                                  akbar[s]
                except:
                    try:
                        temp_partitions += asghar[s - weights[i] * (size + 1)] * \
                                           akbar[s]
                    except:
                        pass
            # info.partitions += temp_partitions
        i += gp_size
    return partitions


def get_different_sums(weights):
    def adjust_size(number, lenngth):
        number = ("0" * (lenngth - len(number))) + number
        return number

    if len(weights) != 0:
        # print(weights)
        sums_array = [0 for i in range(0, weights[-1])]

        counter = adjust_size('0', len(weights))
        one = '1'
        # print(sums_array)
        while counter != '1' + '0' * len(weights):
            counter = adjust_size(counter, len(weights))

            temp_sum = 0
            for i in range(0, len(weights)):
                if counter[i] == '1':
                    temp_sum += weights[i]
            # print("temp sum is : ", temp_sum)
            # print("counter is : ", counter, )
            # print("sums array is : ", sums_array, "\n")
            flag = False

            while flag is False:
                try:
                    sums_array[temp_sum] += 1
                    flag = True
                except:
                    sums_array.append(0)

            counter = bin(int(one, 2) + int(counter, 2))[2:]

        sums_array.append(0)
        return sums_array
    else:
        return [1, 0]


partitions = 0
print(divide(get_weights(), partitions))

# info = Information()
#
# print(get_different_combinations(info , 3 , 3))
# print(get_different_sums_p([]))
# print(get_different_sums([]))
