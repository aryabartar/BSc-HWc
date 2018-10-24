q_info = input('').split(' ')
q_info = [int(i) for i in q_info]

weights = input('').split(' ')
weights = [int(i) for i in weights]
weights.sort()

counter = 0
pointer = q_info[0]
# print(pointer)
for i in range(0, int(q_info[0])):
    # print("i is running")
    # print("pointer : " + str(pointer))
    # print("i : " + str(i))
    for j in range(pointer-1, i-1 , -1):
        # print("j is running")
        if weights[i] + weights[j] <= q_info[1]:
            counter += 1
            pointer = j-1
            break
        else:
            counter += 1

print(counter+1)