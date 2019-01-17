comb = [0] * 33
for i in range(0, 31):
    comb[i] = [0] * 32


def divide(weights):
    numbers = 0
    i = 0
    while i < len(weights):
        gp_size = 0
        asghar = get_different_sums(weights[0: i])
        for j in range(i + 1, len(weights)):
            gp_size += 1
            if weights[j] != weights[i]:
                break
            gp_size += 1

        for t in range(0, gp_size):
            # print(g)
            akbar = get_different_sums(weights[i + t + 1: len(weights)])
            for s in range(weights[i] * (t + 1), 30001):
                # print(s)
                try:
                    numbers += comb1(gp_size, t + 1) * \
                               asghar[s - weights[i] * (t + 1)] * \
                               akbar[s]
                except:
                    pass
        i += gp_size
    return numbers


def comb1(n, k):
    if comb[n][k] > 0:
        return comb[n][k]
    if k == 0:
        return 1
    elif n == k:
        return 1

    comb[n][k] = comb1(n - 1, k) + comb1(n - 1, k - 1)
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
