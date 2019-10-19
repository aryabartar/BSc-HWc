import matplotlib.pyplot as plt
import numpy as np

t = np.arange(-5, 5, 0.001)
y = (np.exp(-t) * np.heaviside(t , 1)) + np.cos(t - 2) 

plt.savefig('Q1b.png')
