import numpy as np
import matplotlib.pyplot as plt

n = np.arange(-10, 11)
x = np.heaviside(n, 1) - np.heaviside(n - 5, 1)

plt.plot(n, x, "o")
plt.savefig('a.png')
plt.show()