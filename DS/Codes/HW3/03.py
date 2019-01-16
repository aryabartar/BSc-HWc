class Price:
    def __init__(self, price):
        self.price = price


flight_data = input().split()
flight_data = [int(x) for x in flight_data]

flight_prices = []
for i in range(0, flight_data[0]):

flight_paths = []

adjacency_graph = [[]]
for i in range(0, 100):
    adjacency_graph.append([])

for i in range(0, flight_data[0]):
    weight = int(input().split()[0])
    paths = [int(x) for x in input().split()]

    for i in range(0, len(paths) - 1):
        adjacency_graph[paths[i]].append((paths[i + 1], weight))

print(adjacency_graph)
