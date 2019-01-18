class Path:
    def __init__(self, cost, cities):
        self.cost = cost
        self.cities = cities

    def print_information(self):
        return "Cost is : " + str(self.cost) + "| Paths are : " + \
               str(self.cities)


def get_inputs():
    transfer_information = [int(x) for x in input().split(" ")]
    paths = []

    for i in range(0, transfer_information[0]):
        cost = int(input().split()[0])
        path_cities = [int(x) for x in input().split(" ")]
        paths.append(Path(cost, path_cities))

    return transfer_information, paths


def print_paths(paths):
    print("---\nPrinting paths information : ")
    for path in paths:
        print(path.print_information())


def get_city_numbers(paths):
    max_city_number = 0
    for path in paths:
        if len(path.cities) > max_city_number:
            max_city_number = len(path.cities)
    return max_city_number


def get_adjacency_matrix(paths):
    def initialize_adjencency_matrix(paths):
        MAX_NUMBER = 10000  # TODO:Change this if we have bugs
        city_numbers = get_city_numbers(paths)
        adjacency_matrix = \
            [[MAX_NUMBER for x in range(city_numbers + 1)] for y in range(city_numbers + 1)]
        return adjacency_matrix

    adjacency_matrix = initialize_adjencency_matrix(paths)
    for path in paths:
        for i in range(0, len(path.cities)):
            for j in range(i + 1, len(path.cities)):
                if path.cost < adjacency_matrix[path.cities[i]][path.cities[j]]:
                    adjacency_matrix[path.cities[i]][path.cities[j]] = path.cost

    print(adjacency_matrix)


transfer_information, paths = get_inputs()
adjacency_matrix = get_adjacency_matrix(paths)
