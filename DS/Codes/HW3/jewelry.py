def B(weights, sum1):
    def mask(lst, m):
        m = m.zfill(len(lst))
        return map(lambda x: x[0], filter(lambda x: x[1] != '0', zip(lst, m)))

    def subset_sum(lst, target):
        for i in range(2 ** len(lst)):
            pick = mask(lst, bin(i)[2:])
            if sum(pick) == target:
                yield pick

    return len(list(subset_sum(weights, sum1)))



def divide(weights):
    max_s = 3000000
    res = 0

    for i in range(0, len(weights)):
        for s in range(weights[i], max_s):
            res += B(i, s - weights[i], weights) * F(len(weights) - 1 - i, s, weights)


def get_weights():
    input()  # Nothing :D
    weights = [int(x) for x in input().split(" ")]
    weights.sort()

    return weights


# weights = get_weights()
# divide(weights)
# print(B([1, 2, 3, 4], 3, 5))


