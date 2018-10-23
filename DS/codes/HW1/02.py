def enqueue(number, queue, undo_queue):
    queue.append(number)
    undo_queue.append((False, number))


def pop(queue, undo_queue):
    undo_queue.append((True, queue.pop(0)))
    return undo_queue[-1][1]


def undo(queue, undo_queue):
    if undo_queue[-1][0]:
        queue = queue + [undo_queue.pop()[1]]
    else:
        queue.pop()
        undo_queue.pop()
    return queue


def detect_string(input_string, queue, undo_queue):
    splitted_string = input_string.split(" ")

    if splitted_string[0] == "enqueue":
        enqueue(int(splitted_string[1]), queue, undo_queue)

    elif splitted_string[0] == "pop":
        print(pop(queue, undo_queue))

    elif splitted_string[0] == "undo":
        queue = undo(queue, undo_queue)

    print(queue)
    print(undo_queue)


queue = []
undo_queue = []
number_of_execution = int(input(''))
for i in range(0, number_of_execution):
    detect_string(input(), queue, undo_queue)
