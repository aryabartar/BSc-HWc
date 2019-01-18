class Path:
    def __init__(self, cost, cities):
        self.cost = cost
        self.cities = cities


def get_inputs():
    transfer_information = [int(x) for x in input().split(" ")]

    paths = []
    for i in range(0, transfer_information[0]):
        cost = int(input().split()[0])
        paths = [int(x) for x in input().split(" ")]

        p = Path(cost, paths)
        paths.append(p)

    return transfer_information, paths
