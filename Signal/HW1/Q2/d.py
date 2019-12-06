import numpy as np
import matplotlib.pyplot as plt

n = np.arange(-10, 11)
x = 5 * np.cos(3 * n)

plt.stem(n, x)
plt.savefig('d.png')
plt.show()
