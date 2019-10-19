import matplotlib.pyplot as plt
import numpy as np

t = np.arange(-5, 5, 0.001)
y = np.exp(-4 * t) * np.heaviside(t , 1)
plt.plot(t , y)

plt.savefig('Q1a.png')
