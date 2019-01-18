class Path:
    def __init__(self, cost, cities):
        self.cost = cost
        self.cities = cities

    def print_information(self):
        return "Cost is : " + str(self.cost) + "| Paths are : " + \
               str(self.cities)


def get_inputs():
    transfer_information = [int(x) for x in input().split(" ")]
    transfer_information[0], transfer_information[2] = transfer_information[2], transfer_information[0]
    paths = []

    for i in range(0, transfer_information[0]):
        cost = int(input().split()[0])
        path_cities = [int(x) for x in input().split(" ")]
        paths.append(Path(cost, path_cities))

    return transfer_information, paths


# def check_error(paths):
#     city_number = 0
#     for path in paths:
#         if len(path.cities) > city_number:
#             city_number = len(path.cities)
#     for path in paths:
#         for i in path.cities:
#             if i > city_number + 1000:
#                 raise ValueError('A very specific bad thing happened.')
#                 pass


# def print_paths(paths):
#     print("---\nPrinting paths information : ")
#     for path in paths:
#         print(path.print_information())


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
            [[MAX_NUMBER for x in range(city_numbers + 1 + 1000)] for y in range(city_numbers + 1 + 1000)]
        path_length_matrix = \
            [[MAX_NUMBER for x in range(city_numbers + 1 + 1000)] for y in range(city_numbers + 1 + 1000)]
        return adjacency_matrix, path_length_matrix

    adjacency_matrix, path_length_matrix = initialize_adjencency_matrix(paths)
    for path in paths:
        for i in range(0, len(path.cities)):
            for j in range(i + 1, len(path.cities)):
                try:
                    if path.cost < adjacency_matrix[path.cities[i]][path.cities[j]]:
                        adjacency_matrix[path.cities[i]][path.cities[j]] = path.cost
                        path_length_matrix[path.cities[i]][path.cities[j]] = j - i
                except:
                    pass

    return adjacency_matrix, path_length_matrix


def dijkstra(adjacency_matrix, start, finish, path_length_matrix):
    def get_city_set(adjacency_matrix):
        a = set()
        for i in range(1, len(adjacency_matrix)):
            a.add(i)
        return a

    def initialize_dijkstra(adjacency_matrix, start):
        cities_set = get_city_set(adjacency_matrix)
        dijkstra_list = [10000] * (len(cities_set) + 1)
        dijkstra_parent_list = [10000] * (len(cities_set) + 1)
        cities_set.remove(start)

        for city in cities_set:
            dijkstra_list[city] = adjacency_matrix[start][city]
            dijkstra_parent_list[city] = start

        return dijkstra_list, dijkstra_parent_list

    dijkstra_list, dijkstra_parent_list = initialize_dijkstra(adjacency_matrix, start)
    visited = {start}
    not_visited = get_city_set(adjacency_matrix)
    not_visited.remove(start)

    while len(not_visited) != 1:
        # print("running while")
        minimum_cost = 10000
        minimum_index_for_updating = -1
        # print(not_visited)
        for i in not_visited:
            if dijkstra_list[i] <= minimum_cost:
                minimum_cost = dijkstra_list[i]
                minimum_index_for_updating = i

        not_visited.remove(minimum_index_for_updating)
        visited.add(minimum_index_for_updating)

        for city in not_visited:
            if (adjacency_matrix[minimum_index_for_updating][city] + dijkstra_list[minimum_index_for_updating]) < \
                    dijkstra_list[city]:
                dijkstra_list[city] = adjacency_matrix[minimum_index_for_updating][city]
                dijkstra_parent_list[city] = minimum_index_for_updating

        current_city = finish
        sum = 0
        while current_city != start:
            parent = dijkstra_parent_list[current_city]
            sum += path_length_matrix[parent][current_city]
            current_city = dijkstra_parent_list[current_city]

    return dijkstra_list[finish], sum


transfer_information, paths = get_inputs()
adjacency_matrix, path_length_matrix = get_adjacency_matrix(paths)
minimum_cost, sum = dijkstra(adjacency_matrix, transfer_information[2], transfer_information[1], path_length_matrix)

if minimum_cost == 10000 or minimum_cost == 10000:
    print("-1 -1")
else:
    print(str(minimum_cost) + " " + str(sum))
