import numpy as np
import matplotlib.pyplot as plt

n = np.arange(-10, 11)
x = (1 - np.exp(0.5 * n)) * np.heaviside(-n + 4, 1) + (3 * np.heaviside(n - 5, 1))

plt.plot(n, x, "o")
plt.savefig('b.png')
plt.show()