class Information:
    def __init__(self):
        self.comb = [[0 for i in range(0, 33)] for j in range(0, 32)]
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
        c1 = get_different_combinations(info, n - 1, k - 1)
        c2 = get_different_combinations(info, n - 1, k)
        info.comb[n][k] = summation([c1, c2])
        return summation([c1, c2])


def get_different_sums(weights):
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
        for j in i :
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
    temp_partitions = 0
    i = 0

    while i < len(weights) - 1:
        gp_size = update_group_size(1, i + 1, i)

        asghar = get_different_sums(weights[0: i])
        for size in range(0, gp_size):
            weights_size = size + 1
            akbar = get_different_sums(weights[i + size + 1: len(weights)])
            temp_partitions = 0

            for s in range(weights[i] * weights_size, 22000):
                try:
                    partitions += get_different_combinations(info, gp_size, size + 1) * \
                                  asghar[s - weights[i] * (size + 1)] * \
                                  akbar[s]

                except:
                    try:
                        temp_partitions += asghar[s - weights[i] * (size + 1)] * \
                                           akbar[s]
                    except:
                        pass
            info.partitions += temp_partitions
        i += gp_size
    return partitions


partitions = 0
print(divide(get_weights(), partitions))
