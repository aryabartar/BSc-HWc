import numpy as np
import matplotlib.pyplot as plt

t = np.arange(-5, 5, 0.001)
y = (np.exp(-t) * np.heaviside(t , 1)) + np.cos(t - 2) 

plt.plot(t , y)
plt.savefig('b.png')
plt.show()