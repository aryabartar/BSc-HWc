def max_heapify(array, i, heap_size):
    # print("========")
    # print(array)
    # print(" i = " + str(i))
    max = i
    if 2 * i < heap_size:
        print("Can check first if")
        if array[i] < array[2 * i]:
            print("First if running")
            max = 2 * i
    if 2 * i + 1 < heap_size:
        print("Can check second if")
        if array[max] < array[2 * i + 1]:
            print("Second if running")
            max = 2 * i + 1
    print(max)
    if not max == i:
        temp = array[max]
        array[max] = array[i]
        array[i] = temp
    print("======")
    return array


def insert_max_heap(number, max_heap_array):
    max_heap_array.append(number)
    i = int((len(max_heap_array) - 1) / 2)

    while i > 0:
        print("i is : " + str(i))
        max_heapify(max_heap_array, i, len(max_heap_array))
        i = int(i / 2)


max_heap_array = [0]
for_number = input("")
for i in range(0, int(for_number)):
    user_input = input("").split(" ")
    if user_input[0] == "1":
        insert_max_heap(int(user_input[1]), max_heap_array)
    else:
        print(max_heap_array)
    # print(max_heap_array)
