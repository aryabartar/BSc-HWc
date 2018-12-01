class MinHeap:
    array = []
    size = 0
    existed_numbers = 0

    def init(self, size):
        self.size = size
        self.array = [0] * size
        self.existed_numbers = 0

    def insert(self, in1):
        self.existed_numbers += 1
        i = self.existed_numbers - 1
        self.array[i] = in1
        while not i == 0 and self.array[int((i - 1) // 2)] > self.array[i]:
            self.swap(i, int((i - 1) // 2))
            i = (i - 1) // 2

    def extractMin(self):
        r = self.array[0]
        self.array[0] = self.array[self.existed_numbers - 1]
        self.existed_numbers -= 1
        self.size -= 1
        self.minHeapify(0)
        return r

    def minHeapify(self, i):
        right = 2 * i + 2
        left = 2 * i + 1
        smallest = i
        if left < self.existed_numbers and self.array[left] < self.array[i]:
            smallest = left
        if right < self.existed_numbers and self.array[right] < self.array[smallest]:
            smallest = right
        if not smallest == i:
            self.swap(i, smallest)
            self.minHeapify(smallest)

    def swap(self, x, y):
        temp = self.array[x]
        self.array[x] = self.array[y]
        self.array[y] = temp


size = int(input(""))

m = MinHeap()
m.init(size)

user_input = input("").split(" ")
for i in range(0, size):
    m.insert(int(user_input[i]))

r = 0
for g in range (0 , size) :
    u = m.extractMin()
    if g == size-1 :
        v = 0
    else:
        v = m.extractMin()
    r += u + v
    m.insert(u+v)
print(r)
