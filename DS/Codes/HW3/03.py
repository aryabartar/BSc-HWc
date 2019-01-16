class Price:
    def __init__(self, price):
        self.price = price

    def __repr__(self):
        return "price : " + str(self.price)


def get_adjecency_graph(flight_data):
    adjacency_graph = [[]]
    for i in range(0, 100):
        adjacency_graph.append([])

    for i in range(0, flight_data[0]):
        weight = Price(int(input().split()[0]))
        paths = [int(x) for x in input().split()]

        for i in range(0, len(paths) - 1):
            adjacency_graph[paths[i]].append((paths[i + 1], weight))

    return adjacency_graph


def start_dijkstra(start_node, end_node, adjacency_list):
    def create_dijkstra_list():
        dijkstra_list = []

        for i in adjacency_list:
            dijkstra_list.append([])


flight_data = [int(x) for x in input().split()]
adjacency_list = get_adjecency_graph(flight_data)
start_dijkstra(flight_data[2], flight_data[1], adjacency_list)
