import matplotlib.pyplot as plt
import numpy as np

t = np.arange(-5, 5, 0.001)
x = [0] * 10000

index = 0
while index < 10000:

    if t[index] < 0:
        x[index] = 0
    else: 
        x[index] = np.exp(-3 * t[index]) - np.exp(-6 * t[index])
    index += 1


plt.plot(t, x)
plt.savefig('Q1c.png')
