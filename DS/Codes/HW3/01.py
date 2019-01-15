def divide(weights):
    for i in reversed(range(0, len(weights))):
        
        for j in reversed(range(0, i)):
            print(weights[j])
        print(" \n ")


def get_weights():
    # input()  # We don't need n
    weights = [1, 2, 5, 3, 4, 5]
    weights_array = []

    for c in weights:
        if c != " ":
            weights_array += [int(c)]

    weights_array.sort()

    return weights_array


weights = get_weights()
divide(weights)
