class Path:
    def __init__(self, cost, cities):
        self.cost = cost
        self.cities = cities

    def print_information(self):
        return "Cost is : " + str(self.cost) + "| Paths are : " + \
               str(self.cities)


def print_paths(paths):
    print("---\nPrinting paths information : ")
    for path in paths:
        print(path.print_information())


def get_inputs():
    transfer_information = [int(x) for x in input().split(" ")]

    paths = []
    for i in range(0, transfer_information[0]):
        cost = int(input().split()[0])
        path_cities = [int(x) for x in input().split(" ")]

        paths.append(Path(cost, path_cities))

    return transfer_information, paths


transfer_information, paths = get_inputs()
print_paths(paths)
print(transfer_information)
