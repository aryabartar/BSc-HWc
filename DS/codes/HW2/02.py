total_array_count = int(input(""))
array_counts = input("").split(" ")

total_array = [0]
for i in range(0, total_array_count):
    total_array.append(int(array_counts[i]))

total_array += [0] * 50
print(total_array)
i = 1
total_price = 0
while i < 20:
    if total_array[i] % 2 == 0:
        total_price += i * total_array[i]
        total_array[i * 2] += int(total_array[i] / 2)
        total_array[i] = 0
    else:
        total_price += i * total_array[i]
        next_group_index
        total_array[i * 2] += int(total_array[i] / 2)
        total_array[i] = 0

    i += 1
    print(total_array)

print(total_array)
