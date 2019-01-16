def get_weights():
    # input()  # Nothing :D
    weights = [int(x) for x in [1, 2, 3, 4]]
    weights.sort()

    return weights


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


def F():
    pass


def divide(weights):
    max_s = 30
    res = 0

    for i in range(0, len(weights)):
        print(i)
        for s in range(weights[i], max_s):
            res += B(weights[0:i + 1], s - weights[i]) * B(weights[i + 1:], s)

    return res


weights = get_weights()
print("res is : ", divide([1, 2, 3, 4]))
