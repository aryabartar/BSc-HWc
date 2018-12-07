class Queue:
    def __init__(self):
        self.list = []

    def enqueue(self, x):
        self.list.append(x)

    def dequeue(self):
        temp = self.list[0]
        del self.list[0]
        return temp

    def print_state(self):
        print(self.list)

    def is_empty(self):
        if len(self.list) == 0:
            return True
        return False


class Vertex:
    def __init__(self, value):
        self.value = value
        self.color = None
        self.p = None
        self.d = None

    def set_white(self):
        self.color = "WHITE"

    def set_gray(self):
        self.color = "GRAY"

    def set_black(self):
        self.color = "BLACK"

    def __repr__(self):
        # return "Value : " + str(self.value) + " | Color : " + str(self.color )+ " | P : " + str(self.p)+ "==="
        return "Value : " + str(self.value)


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

    queue = Queue()
    queue.enqueue(s)

    while not queue.is_empty():
        u = queue.dequeue()
        # for v in


def get_edges_and_vertex():
    number_of_v = int(input(""))
    edges = {}
    vertexes = [None]

    for i in range(1, number_of_v + 1):
        temp_vertex = Vertex(i)
        edges[i] = []
        vertexes.append(Vertex(temp_vertex))

    # print(edges)
    # print(vertexes)
    for i in range(1, number_of_v):
        edge = input('').split(' ')
        edge[0] = int(edge[0])
        edge[1] = int(edge[1])

        # print(edge[0])
        # print(edge[1])
        # print("==")

        edges[int(edge[1])].append(vertexes[edge[0]])
        edges[int(edge[0])].append(vertexes[edge[1]])

    return edges, vertexes


edges, vertexes = get_edges_and_vertex()
# edges = {1: [2], 2: [1, 3, 4], 3: [2], 4: [2]}
# vertexes = [1, 2, 3, 4]
# BFS(edges, vertexes, vertexes[0])

print(edges)
print(vertexes)
