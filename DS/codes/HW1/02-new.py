class Queue :
    def __init__(self):
        self.main_queue = []
        self.second_queue = []

    def enqueue(self, number):
        self.second_queue.append((True, number))
        self.main_queue.append(number)


    def pop(self):
        popped_value = self.main_queue.pop(0)
        self.second_queue.append((False, popped_value))
        return popped_value

    def undo(self):
        if self.second_queue[-1][0]:
            self.main_queue.pop()
            self.second_queue.pop()
        else:
            self.main_queue.insert(0, self.second_queue.pop()[1])


def main():
    queue = Queue()

    for i in range(0, int(input())):
        message = str(input())
        main_message = message.split(" ")

        if main_message[0] == "enqueue":
            queue.enqueue(int(main_message[1]))

        elif main_message[0] == "pop":
            print(queue.pop())

        elif main_message[0] == "undo":
            queue.undo()

main()