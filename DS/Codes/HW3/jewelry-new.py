class Information:
    def __init__(self):
        self.comb = [[0 for i in range(0, 33)] for j in range(0, 32)]


def get_weights():
    input()  # Nothing :D
    weights = [int(x) for x in input().split(" ")]
    weights.sort()

    return weights


def comb1(info, n, k):
    if info.comb[n][k] > 0:
        return info.comb[n][k]
    elif k == 0:
        return 1
    elif n == k:
        return 1
    else:
        c1 = comb1(info, n - 1, k - 1)
        c2 = comb1(info, n - 1, k)
        info.comb[n][k] = c1 + c2
        return c1 + c2


def get_sums(weights):
    sum = 0
    for item in weights:
        sum += item
    return sum


def get_different_sums(weights):
    sum_ways = [1] + [0] * (get_sums(weights) + 1)

    for i in range(0, len(weights)):
        # print(i)
        j = get_sums(weights)

        while j >= weights[i]:
            sum_ways[j] += sum_ways[j - weights[i]]
            j -= 1

    return sum_ways


def divide(weights, partitions):
    info = Information()
    i = 0
    while i < len(weights) - 1:
        gp_size = 1

        j = i + 1
        while j < len(weights) and weights[i] == weights[j]:
            gp_size += 1
            j += 1

        asghar = get_different_sums(weights[0: i])

        for size in range(0, gp_size):
            # print(g)
            akbar = get_different_sums(weights[i + size + 1: len(weights)])
            for s in range(weights[i] * (size + 1), 25000):
                # print(s)
                try:
                    partitions += comb1(info, gp_size, size + 1) * \
                                  asghar[s - weights[i] * (size + 1)] * \
                                  akbar[s]
                except:
                    # nothing
                    pass
        i += gp_size
    return partitions


partitions = 0
print(divide(get_weights(), partitions))
