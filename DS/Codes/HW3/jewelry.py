def divide(weights):
    print(weights)


def get_weights():
    input()  # Nothing :D
    weights = [int(x) for x in input().split(" ")]
    weights.sort()

    return weights


weights = get_weights()
divide(weights)
