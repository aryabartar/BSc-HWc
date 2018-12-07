class Queue:
    def __init__(self):
        self.list = []

    def enqueue(self, x):
        self.list.append(x)

    def dequeue(self):
        del self.list[0]

    def print_state(self):
        print(self.list)


class Vertex:
    def __init__(self, value):
        self.value = value
        self.color = None
        self.p = None

    def set_white(self):
        self.color = "WHITE"

    def set_gray(self):
        self.color = "GRAY"

    def set_black(self):
        self.color = "BLACK"


def BFS(edges, vertexes, s):
    vertexes_without_s = vertexes

    for vertex in vertexes_without_s:
        if vertex == s:  # For -s
            continue

        vertex.set_white()
        vertex.value = -1
        vertex.p = None

    s.set_gray()
    s.value = 0
    s.p = None
    queue = Queue


def get_edges_and_vertex():
    number_of_v = int(input(""))
    edges = {}
    vertexes = []

    for i in range(1, number_of_v + 1):
        edges[i] = []
        vertexes.append(Vertex(i))

    for i in range(1, number_of_v):
        edge = input('').split(' ')
        edge[0] = int(edge[0])
        edge[1] = int(edge[1])

        edges[edge[0]].append(edge[1])
        edges[edge[1]].append(edge[0])

    return edges, vertexes


# edges, vertexes = get_edges_and_vertex()
# edges = {1: [2], 2: [1, 3, 4], 3: [2], 4: [2]}
# vertexes = [1, 2, 3, 4]
# BFS(edges, vertexes, vertexes[0])

