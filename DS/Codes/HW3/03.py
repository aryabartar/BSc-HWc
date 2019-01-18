MAX_NUMBER = 1000000000000


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


def get_city_numbers(paths):
    max_city_number = 0
    for path in paths:
        if len(path.cities) > max_city_number:
            max_city_number = len(path.cities)
    return max_city_number


def get_adjacency_matrix(paths):
    def initialize_adjencency_matrix(paths):
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
                    elif j - i < path_length_matrix[path.cities[i]][path.cities[j]] and \
                            path.cost == adjacency_matrix[path.cities[i]][path.cities[j]]:
                        path_length_matrix[path.cities[i]][path.cities[j]] = j - 1
                except:
                    pass

    return adjacency_matrix, path_length_matrix


def dijkstra(adjacency_matrix, start, finish, path_length_matrix):
    def get_city_set(adjacency_matrix):
        a = set()
        for i in range(1, len(adjacency_matrix)):
            a.add(i)
        return a

    def initialize_dijkstra(adjacency_matrix, start, path_length_matrix):
        cities_set = get_city_set(adjacency_matrix)
        dijkstra_list = [MAX_NUMBER] * (len(cities_set) + 1)
        dijkstra_parent_list = [MAX_NUMBER] * (len(cities_set) + 1)
        cities_set.remove(start)

        for city in cities_set:
            dijkstra_list[city] = adjacency_matrix[start][city]
            if path_length_matrix[start][city] != MAX_NUMBER:
                dijkstra_parent_list[city] = path_length_matrix[start][city]

        return dijkstra_list, dijkstra_parent_list

    dijkstra_list, dijkstra_parent_list = initialize_dijkstra(adjacency_matrix, start, path_length_matrix)
    visited = {start}
    not_visited = get_city_set(adjacency_matrix)
    not_visited.remove(start)

    print(dijkstra_parent_list[0:20])
    while len(not_visited) != 1:
        minimum_cost = 10000000000000
        minimum_index_for_updating = -1

        for i in not_visited:
            if dijkstra_list[i] <= minimum_cost:
                minimum_cost = dijkstra_list[i]
                minimum_index_for_updating = i

        # print(minimum_index_for_updating)
        not_visited.remove(minimum_index_for_updating)
        visited.add(minimum_index_for_updating)

        for city in not_visited:
            if (adjacency_matrix[minimum_index_for_updating][city] + dijkstra_list[minimum_index_for_updating]) < \
                    dijkstra_list[city]:
                dijkstra_list[city] = adjacency_matrix[minimum_index_for_updating][city] + dijkstra_list[
                    minimum_index_for_updating]

                if dijkstra_parent_list[city] == MAX_NUMBER :
                    dijkstra_parent_list[city] = path_length_matrix[minimum_index_for_updating][city] + dijkstra_parent_list[minimum_index_for_updating]
                else :
                    # print("Running else and path_length_matrix[minimum_index_for_updating][city] is : " ,
                    #       path_length_matrix[minimum_index_for_updating][city])
                    dijkstra_parent_list[city] = path_length_matrix[minimum_index_for_updating][city]+ dijkstra_parent_list[minimum_index_for_updating]

                # print("minimum is : ", minimum_index_for_updating, " City is : ", city)
                # print(dijkstra_parent_list)
                # print("")

    # print("jdfkjfd" , path_length_matrix[3][1])
    return dijkstra_list[finish], dijkstra_parent_list[finish]


transfer_information, paths = get_inputs()
adjacency_matrix, path_length_matrix = get_adjacency_matrix(paths)
minimum_cost, sum = dijkstra(adjacency_matrix,
                             transfer_information[2],
                             transfer_information[1],
                             path_length_matrix)

if minimum_cost == MAX_NUMBER or sum == MAX_NUMBER:
    # print(minimum_cost, " ", sum)
    print("-1 -1")
else:
    print(str(minimum_cost) + " " + str(sum))
