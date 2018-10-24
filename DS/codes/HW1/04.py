q_info = input('').split(' ')
q_info = [int(i) for i in q_info]

weights = input('').split(' ')
weights = [int(i) for i in weights]
weights.sort()

counter = 0
pointer = q_info[0]-1

for i in range(0, int(q_info[0])):
    # print ("&&&First if (i) running i is : " + str(i))
    # print("&&&Pointer is : " + str(pointer))
    if pointer == i :
        counter += 1
        break
    for j in range(pointer, i-1 , -1):
        # print("========")
        # print("i is : " + str(i))
        # print("j is : " + str(j))
        # print("pointer is : " + str(pointer))
        # print("Counter is : " + str(counter))
        if weights[i] + weights[j] <= q_info[1]:
            # print("First if run.")
            counter += 1
            pointer = j-1
            # print("========")
            break
        else:
            # print("Else run")
            counter += 1
            # print("========")

print(counter)