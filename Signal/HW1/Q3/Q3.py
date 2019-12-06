import numpy as np
import matplotlib.pyplot as plt

t = np.arange(-10, 10, 0.001)

gaussian_noise = 0.008 * np.random.normal(0, 1, 20000)
y = np.heaviside(t + 2, 1) - np.heaviside(t - 2, 1)
y_with_noise = np.heaviside(t + 2, 1) - np.heaviside(t - 2, 1) + gaussian_noise

plt.plot(t, y)
plt.savefig('y.png')

plt.plot(t, y_with_noise)
plt.savefig('y_with_noise.png')

