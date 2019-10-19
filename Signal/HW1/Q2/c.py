import numpy as np
import matplotlib.pyplot as plt

n = np.arange(-10, 11)
x = 5 * np.cos(3 * np.pi * n)

plt.stem(n, x)
plt.savefig('c.png')
plt.show()
