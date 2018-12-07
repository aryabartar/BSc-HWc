number_of_v = int(input(""))
edges = {}

for i in range(1, number_of_v + 1):
    edges[i] = []

for i in range(1, number_of_v):
    edge = input('').split(' ')
    edge[0] = int(edge[0])
    edge[1] = int(edge[1])

    edge_tuple = (edge[0], edge[1])
    edges[edge[0]].append(edge[1])
    edges[edge[1]].append(edge[0])

print(edges)


