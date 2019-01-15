def get_weights():
    input()  # We don't need n
    weights = input()
    weights_array = []

    for c in weights :
        if c != " " :
            weights_array += [int(c)]

    weights_array.sort()

    return weights_array


print(get_weights())
