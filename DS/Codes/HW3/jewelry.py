import math

def get_weights():
    input()  # Nothing :D
    weights = [int(x) for x in input().split(" ")]
    weights.sort()

    return weights

def nCr(n,r):
    f = math.factorial
    return f(n) / f(r) / f(n-r)



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
    res = 0
    # print("ddd")
    for i in range(0, len(weights)):
        print("i is : ", i)
        max_s = int(weights[i] * (weights[i] + 1)) + 1
        # max_s = 1000
        for s in range(weights[i], max_s):
            lowers = B(weights[0:i], s - weights[i])
            highers = B(weights[i + 1:], s)

            print("lowers is : ", lowers)
            print("highers : ", highers)
            print("weights[0:i + 1] is : ", weights[0:i])
            print("s - weights[i] is : ", s - weights[i])
            print("weights[i + 1:] is : ", weights[i + 1:])
            print("s is : ", s)

            res += lowers * highers

            print("\n")
        #
        print("res is : ", res)
        print("---------")

    return res


weights = get_weights()
print(divide(weights))



