class Stack:
    def __init__(self):
        self.container = []

    def push(self, item):
        self.container.append(item)

    def pop(self):
        return self.container.pop()

    def spell(self):
        bar = map(int, self.container)
        return min(bar)

def detect_string(string, stack):
    splitted_string = string.split(" ")
    if splitted_string[0] == "push":
        stack.push(int(splitted_string[1]))
    elif splitted_string[0] == "pop":
        stack.pop()
    elif splitted_string[0] == "spell":
        print(stack.spell())
    return stack


stack = Stack()

number_of_execution = int(input(''))
for i in range(0, number_of_execution):
    stack = detect_string(input(), stack)
