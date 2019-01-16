def get_adjecency_graph() :
    class Price:
        def __init__(self, price):
            self.price = price

        def __repr__(self):
            return "price : " + str(self.price)


    flight_data = [int(x) for x in input().split()]

    flight_prices = []
    flight_paths = []

    adjacency_graph = [[]]
    for i in range(0, 100):
        adjacency_graph.append([])

    for i in range(0, flight_data[0]):
        weight = Price(int(input().split()[0]))
        paths = [int(x) for x in input().split()]

        for i in range(0, len(paths) - 1):
            adjacency_graph[paths[i]].append((paths[i + 1], weight))

    return adjacency_graph

print(get_adjecency_graph())