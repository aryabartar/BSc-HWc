q_info = input('').split(' ')
q_info = [int(i) for i in q_info]

weights = input('').split(' ')
weights = [int(i) for i in weights]
weights.sort()

counter = 0

i = 0
t = len(weights) - 1

while i <= t:
    counter += 1

    if weights[i] + weights[t] <= q_info[1]:
        i += 1
        t -= 1

    else:
        t -= 1

print(counter)
