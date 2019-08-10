def push(main_stack, min_stack, number):
    if min_stack.is_empty():
        min_stack.push(number)
    else:
        if number <= min_stack.container[-1]:
            min_stack.push(number)
    main_stack.push(number)


def pop(main_stack, min_stack):
    popped_number = main_stack.pop()
    if min_stack.top() == popped_number:
        min_stack.pop()


class Stack:
    def __init__(self):
        self.container = []

    def push(self, item):
        self.container.append(item)

    def pop(self):
        return self.container.pop()

    def top(self):
        return self.container[-1]

    def is_empty(self):
        if len(self.container) == 0:
            return True
        return False


def detect_string(string, main_stack, min_stack):
    splitted_string = string.split(" ")
    if splitted_string[0] == "push":
        push(main_stack, min_stack, int(splitted_string[1]))
    elif splitted_string[0] == "pop":
        pop(main_stack, min_stack)
    elif splitted_string[0] == "spell":
        print(min_stack.top())



main_stack = Stack()
min_stack = Stack()
number_of_execution = int(input(''))
for i in range(0, number_of_execution):
    detect_string(input(), main_stack, min_stack)
