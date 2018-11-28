def max_heapify(array, i):
    max = i
    if array[i] < array[2 * i]:
        max = 2 * i
        print("111")
    if array[max] < array[2 * i + 1]:
        max = 2 * i + 1
        print("222")
    if not max == i:
        temp = array[max]
        array[max] = array[i]
        array[i] = temp

    return array


def input_max_heap(number, max_heap_array):
    max_heap_array.append(number)


max_heap_array = [0]
for_number = input("")
for i in range(0, int(for_number)):
    user_input = input("").split(" ")
    if user_input[0] == "1":
        input_max_heap(int(user_input[1]), max_heap_array)
    else:
        pass
    print(max_heap_array)

