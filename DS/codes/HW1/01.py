def push(main_stack, min_stack, number):
    if number <= min_stack[-1]:
        min_stack.push(number)
    main_stack.push(number)


def pop(main_stack, min_stack):
    popped_number = main_stack.pop()
    if min_stack.top() == popped_number :
        min_stack.pop()


class Stack:
    def __init__(self):
        self.container = []

    def push(self, item):
        self.container.append(item)

    def pop(self):
        return self.container.pop()

    def top(self) :
        return self.container[-1]

def detect_string(string, stack):
    splitted_string = string.split(" ")
    main_stack = Stack()
    min_stack = Stack()
    if splitted_string[0] == "push":
        pass
    elif splitted_string[0] == "pop":
        pass
    elif splitted_string[0] == "spell":
        pass
    return stack


stack = Stack()

number_of_execution = int(input(''))
for i in range(0, number_of_execution):
    stack = detect_string(input(), stack)
