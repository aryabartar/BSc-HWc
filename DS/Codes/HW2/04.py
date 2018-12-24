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
        return "  Value : " + str(self.value) + "       | Color : " + str(self.color) + "       | P : " + \
               str(self.p) + "       | D : " + str(self.d) + "\n"


def BFS(edges, vertexes, s):
    vertexes_without_s = vertexes

    for vertex in vertexes_without_s:
        if vertex == s or vertex is None:  # For -s
            continue

        vertex.set_white()
        vertex.d = -1
        vertex.p = None

    s.set_gray()
    s.d = 0
    s.p = None

    queue = Queue()
    queue.enqueue(s)

    while not queue.is_empty():
        u = queue.dequeue()
        for v in edges[u.value]:
            if v.color == "WHITE":
                v.set_gray()
                v.d = u.d + 1
                v.p = u
                queue.enqueue(v)
        u.set_black()

    return vertexes


def get_edges_and_vertex(number_of_v):
    edges = {}
    vertexes = [None]

    for i in range(1, number_of_v + 1):
        temp_vertex = Vertex(i)
        edges[i] = []
        vertexes.append(temp_vertex)

    for i in range(1, number_of_v):
        edge = input('').split(' ')
        edge[0] = int(edge[0])
        edge[1] = int(edge[1])

        edges[int(edge[1])].append(vertexes[edge[0]])
        edges[int(edge[0])].append(vertexes[edge[1]])

    return edges, vertexes


def get_biggest_d(vertexes):
    max_d = vertexes[1].d
    return_v = vertexes[1]
    for v in vertexes:
        if v is None:
            continue
        if v.d > max_d:
            max_d = v.d
            return_v = v

    return return_v


def reset_vertexes(vertexes):
    for v in vertexes:
        if v is None:
            continue
        v.color = None
        v.p = None
        v.d = None


number_of_v = int(input(""))
edges, vertexes = get_edges_and_vertex(number_of_v)

vertexes = BFS(edges, vertexes, vertexes[1])
v = get_biggest_d(vertexes)

reset_vertexes(vertexes)

vertexes = vertexes = BFS(edges, vertexes, v)
v = get_biggest_d(vertexes)

print(v.d)
