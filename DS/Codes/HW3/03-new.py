MAX_ELEMENT = 30
comb = [0] * 33
for i in range(0, 31):
    comb[i] = [0] * (MAX_ELEMENT + 32)


def divide(weights):
    MAX_SUM = 30000

    weights.sort()
    ret = 0
    i = 0
    while i < len(weights) - 1:
        ways_below = get_different_sums(weights[0: i])
        group_size = 1
        for j in range(i + 1, len(weights)):
            if weights[j] != weights[i]:
                break
            group_size += 1

        for g in range(0, group_size):
            print(g)
            ways_above = get_different_sums(weights[i + g + 1: len(weights)])
            for s in range(weights[i] * (g + 1), MAX_SUM + 1):
                print(s)
                try:
                    ret += comb1(group_size, g + 1) * ways_below[s - weights[i] * (g + 1)] * ways_above[s]
                except:
                    pass
        i += group_size
    return ret


def comb1(n, k):
    if comb[n][k] > 0:
        return comb[n][k]
    if k == 0 or n == k:
        return 1
    comb[n][k] = comb1(n - 1, k - 1) + comb1(n - 1, k)
    return comb[n][k]


def get_sums(weights):
    sum = 0
    for item in weights:
        sum += item
    return sum


def get_different_sums(weights):
    sum_ways = [0] * (get_sums(weights) + 1)
    sum_ways[0] = 1
    for i in range(0, len(weights)):
        # print(i)
        j = get_sums(weights)
        while j >= weights[i]:
            sum_ways[j] += sum_ways[j - weights[i]]
            j -= 1
    return sum_ways


def get_weights():
    input()  # Nothing :D
    weights = [int(x) for x in input().split(" ")]
    weights.sort()

    return weights

print(divide([1, 2, 3, 4, 5, 5]))
