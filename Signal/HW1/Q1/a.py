import numpy as np
import matplotlib.pyplot as plt

t = np.arange(-5, 5, 0.001)
y = np.exp(-4 * t) * np.heaviside(t , 1)
plt.plot(t , y)

plt.savefig('a.png')
