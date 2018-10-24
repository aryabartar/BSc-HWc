def push(array, para):
    array.append(para)


def pop(array):
    return array.pop()


class Para:
    def __init__(self, value, index):
        self.value = value
        self.index = index
        self.assign_index = 0

    def __str__(self):
        return str(self.value)


string_len = input('')
string = input('')
array = []
index_array = [0] * int(string_len)

for i in range(0, int(string_len)):
    if string[i] == "(":
        push(array, Para('(', i + 1))
    elif string[i] == ')':
        temp_popped_para = pop(array)
        temp_para = Para(')', i + 1)
        temp_para.assign_index = temp_popped_para.index
        temp_popped_para.assign_index = temp_para.index

        index_array[temp_para.index - 1] = temp_para.assign_index
        index_array[temp_popped_para.index - 1] = temp_popped_para.assign_index


print(index_array)
