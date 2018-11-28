def max_heapify(array, i, heap_size):
    max = i
    if 2 * i < heap_size:
        if array[i] < array[2 * i]:
            max = 2 * i
    if 2 * i + 1 < heap_size:
        if array[max] < array[2 * i + 1]:
            max = 2 * i + 1
    if not max == i:
        temp = array[max]
        array[max] = array[i]
        array[i] = temp

    return array


def insert_max_heap(number, max_heap_array):
    max_heap_array.append(number)
    i = int(len(max_heap_array) / 2)
    while i > 0:
        max_heapify(max_heap_array, i, len(max_heap_array))
        i = int(i / 2)
    print(max_heap_array)


max_heap_array = [0]
for_number = input("")
for i in range(0, int(for_number)):
    user_input = input("").split(" ")
    if user_input[0] == "1":
        insert_max_heap(int(user_input[1]), max_heap_array)
    else:
        pass
    print(max_heap_array)

