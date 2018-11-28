def max_heapify(array, i):
    # print("========")
    # print(array)
    # print(" i = " + str(i))
    heap_size = len(array)
    max = i
    if 2 * i < heap_size:
        # print("Can check first if")
        if array[i] < array[2 * i]:
            # print("First if running")
            max = 2 * i
    if 2 * i + 1 < heap_size:
        # print("Can check second if")
        if array[max] < array[2 * i + 1]:
            # print("Second if running")
            max = 2 * i + 1
    # print(max)
    if not max == i:
        temp = array[max]
        array[max] = array[i]
        array[i] = temp
        max_heapify(array, max)
    # print("======")


def build_max_heap(heap_array):
    for i in reversed(range(1, int(len(heap_array) / 2) + 1)):
        print(i)
        max_heapify(heap_array, i)
        print(heap_array)
    print(heap_array)


def insert_heap(number, heap_array):
    heap_array.append(number)


#
# heap_array = [0]
# for_number = input("")
# for i in range(0, int(for_number)):
#     user_input = input("").split(" ")
#     if user_input[0] == "1":
#         insert_heap(int(user_input[1]), heap_array)
#     else:
#         print(heap_array)
#         # return_index = len(heap_array) - int(len(max_heap_array) / 3)
#         # print(return_index)
#         # print(max_heap_array[return_index])
#     # print(max_heap_array)

build_max_heap([0, 1, 7, 9, 21, 8, 5, 9])
