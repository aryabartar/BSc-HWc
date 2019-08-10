import random
number_of_experiment = [10, 100, 1000, 10000]
exp2_n = 0
exp2_f = 0
a = b = a_prime = b_prime = None

for key in number_of_experiment :
    print(key , ": ")
    exp2_n = exp2_f = a = b = 0
    while key > 0:
        a = random.randint(0, 1)
        b = random.randint(0, 1)

        if random.randint(0, 1) == 0:
            a_prime = a
            b_prime = b
        else:
            a_prime = b
            b_prime = a

        if a_prime == 1:
            key -= 1
            exp2_n += 1

            if b_prime == 1:
                exp2_f += 1

    print(exp2_f / exp2_n)

