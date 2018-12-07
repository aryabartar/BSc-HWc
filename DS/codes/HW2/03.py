def get_edges_and_vertex():
    number_of_v = int(input(""))
    edges = {}
    vertexes = []

    for i in range(1, number_of_v + 1):
        edges[i] = []
        vertexes.append(i)

    for i in range(1, number_of_v):
        edge = input('').split(' ')
        edge[0] = int(edge[0])
        edge[1] = int(edge[1])

        edge_tuple = (edge[0], edge[1])
        edges[edge[0]].append(edge[1])
        edges[edge[1]].append(edge[0])

    return edges, vertexes


edges, vertexes = get_edges_and_vertex()

