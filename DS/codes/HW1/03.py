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
beauty = -10

for i in range(0, int(string_len)):
    if string[i] == "(":
        push(array, Para('(', i + 1))
    elif string[i] == ')':
        temp_popped_para = pop(array)
        temp_para = Para(')', i + 1)
        temp_para.assign_index = temp_popped_para.index
        temp_popped_para.assign_index = temp_para.index

        if beauty < temp_para.index - temp_para.assign_index:
            beauty = temp_para.index - temp_para.assign_index
        if beauty < temp_popped_para.index - temp_popped_para.assign_index:
            beauty = temp_popped_para.index - temp_popped_para.assign_index

print(beauty)
