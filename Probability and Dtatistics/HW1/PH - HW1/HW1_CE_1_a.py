import random

number_of_experiment = [10, 100, 1000, 10000]
exp1_n = 0
exp1_f = 0
a = b = None

for key in number_of_experiment:
    a = b = exp1_n = exp1_f = 0
    print(key, ": ")
    while key > 0:
        a = random.randint(0, 1)
        b = random.randint(0, 1)

        if a == 1:
            key -= 1
            exp1_n += 1
            if b == 1:
                exp1_f += 1

    print(exp1_f / exp1_n)
