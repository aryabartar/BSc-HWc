import matplotlib.pyplot as plt
import numpy as np

mu, sigma = 0, 1

for n in [10, 100, 1000, 10000]:
    numbers = np.random.normal(mu, sigma, n)
    print("\n\n\n n =", n)
    plt.xlim(-10, 10)
    plt.hist(numbers, bins=20, normed=True)
    plt.show()


def normal_function(x, mu, var):
    return (1 / (np.sqrt(2 * np.pi * var))) * (np.exp(-(x - mu) ** 2 / (2 * var)))


x = np.arange(-10, 10, 0.01)
var = sigma * sigma
y = [normal_function(t, mu, var) for t in x]
plt.xlim(-10, 10)
plt.plot(x, y)
plt.show()


def bionomial(n, p):
    s = 0
    for i in range(0, n):
        r = np.random.uniform(0, 1)
        if r < p:
            s += 1
    return s


number_of_iteration = 1000
p = 0.7
for n in [10, 100, 1000, 10000]:
    l = []
    for i in range(0, number_of_iteration):
        l.append(bionomial(n, p))
    plt.hist(l, bins=40)
    plt.show()

n = 10000
lam = 3
poisson = np.random.poisson(lam, n)
plt.ylim(0, 2)
plt.xlim(0, 10)
plt.hist(poisson, bins=40, normed=True)
plt.show()

number_of_iteration = 1000

for (n, p) in [(10, 0.3), (100, 0.03), (1000, 0.003), (10000, 0.0003)]:
    l = []
    for i in range(0, number_of_iteration):
        l.append(bionomial(n, p))
    plt.ylim(0, 2)
    plt.xlim(0, 10)
    print("\n\n\n (n,p) =", (n, p))
    plt.hist(l, bins=40, normed=True)
    plt.show()

print("Poisson")
plt.ylim(0, 2)
plt.xlim(0, 10)
plt.hist(poisson, bins=40, normed=True)
plt.show()
