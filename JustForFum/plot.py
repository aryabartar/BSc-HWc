import matplotlib.pyplot as plt

pie = 3.14159 / 4
e = 2 * 1e5

t = float(input("enter T between 50 and 100: \n"))
t = t + 500
fy1 = float(input("enter fy1: \n"))
fy2 = float(input("enter fy2: \n"))
phi_c = float(input("enter phi_c: \n"))
phi_s = float(input("enter phi_s: \n"))
fc1 = float(input("enter fc1: \n"))
fc2 = float(input("enter fc2: \n"))
mill = float(input("enter rebar diameter: \n"))

alpha1 = 0.85 - 0.0015 * fc1
alpha2 = 0.85 - 0.0015 * fc2
beta1 = 0.97 - 0.0025 * fc1
beta2 = 0.97 - 0.0025 * fc2

# noghteye 1
z1 = 0.0035
e1 = z1 + (t - 70) / t * (0.0035 - z1)
e2 = z1 + (t - (t - 300) / 2 - 70) / t * (0.0035 - z1)
e3 = z1 + (0.0035 - z1) / 2
e4 = z1 + (t - (t - 300) / 2 - 230) / t * (0.0035 - z1)
e5 = z1 + 70 / t * (0.0035 - z1)

f1 = min(e1 * e, fy2)
f2 = min(e2 * e, fy1)
f3_1 = min(e3 * e, fy1)
f3_2 = min(e3 * e, fy2)
f4 = min(e4 * e, fy1)
f5 = min(e5 * e, fy2)

C1 = alpha1 * phi_c * fc1 * 300 * 300
C2 = alpha2 * phi_c * fc2 * (t * t - 300 * 300)

t1 = 3 * pie * (mill * mill) * (phi_s * f1 - alpha2 * phi_c * fc2)
t2 = 3 * pie * (mill * mill) * (phi_s * f2 - alpha1 * phi_c * fc1)
t3_1 = 2 * pie * (mill * mill) * (phi_s * f3_1 - alpha1 * phi_c * fc1)
t3_2 = 2 * pie * (mill * mill) * (phi_s * f3_2 - alpha2 * phi_c * fc2)
t4 = 3 * pie * (mill * mill) * (phi_s * f4 - alpha1 * phi_c * fc1)
t5 = 3 * pie * (mill * mill) * (phi_s * f5 - alpha2 * phi_c * fc2)

Nr1 = (C1 + C2 + t1 + t2 + t3_1 + t3_2 + t4 + t5) / 1000
Mr1 = 0
k1 = Nr1
j1 = Mr1

# noghteye 2
z2 = 0.0
e1 = z2 + (t - 70) / t * (0.0035 - z2)
e2 = z2 + (t - (t - 300) / 2 - 70) / t * (0.0035 - z2)
e3 = z2 + (0.0035 - z2) / 2
e4 = z2 + (t - (t - 300) / 2 - 230) / t * (0.0035 - z2)
e5 = z2 + 70 / t * (0.0035 - z2)

f1 = min(e1 * e, fy2)
f2 = min(e2 * e, fy1)
f3_1 = min(e3 * e, fy1)
f3_2 = min(e3 * e, fy2)
f4 = min(e4 * e, fy1)
f5 = min(e5 * e, fy2)

C1 = alpha1 * phi_c * (fc1 - fc2) * beta1 * 300 * 300
C2 = alpha2 * phi_c * fc2 * (t * beta2 * t)

t1 = 3 * pie * (mill * mill) * (phi_s * f1 - alpha2 * phi_c * fc2)
t2 = 3 * pie * (mill * mill) * (phi_s * f2 - alpha1 * phi_c * fc1)
t3_1 = 2 * pie * (mill * mill) * (phi_s * f3_1 - alpha1 * phi_c * fc1)
t3_2 = 2 * pie * (mill * mill) * (phi_s * f3_2 - alpha2 * phi_c * fc2)
t4 = 3 * pie * (mill * mill) * (phi_s * f4 - alpha1 * phi_c * fc1)
t5 = 3 * pie * (mill * mill) * (phi_s * f5 - alpha2 * phi_c * fc2)

Nr2 = (C1 + C2 + t1 + t2 + t3_1 + t3_2 + t4 + t5) / 1000
Mr2 = (((C2 + C1) * (t / 2 - beta2 * (t / 2)) + (t1 * (t / 2 - 70)) + (t2 * 80) - (t4 * 80) - (t5 * (t / 2 - 70))) * (
        10 ^ (-6)))
k2 = Nr2
j2 = Mr2

# noghteye 3
z3 = (0.245 + 0.002 * t) / (t - 70)
x1 = t * 0.0035 / (0.0035 + z3)
x2 = t - x1

e1 = (x1 - 70) / x1 * 0.0035
e2 = (x1 - (t - 300) / 2 - 70) / x1 * 0.0035
e3 = (x1 - (t / 2)) / x1 * 0.0035
e4 = (x1 - (t - 300) / 2 - 230) / x1 * 0.0035 if (t - 300) / 2 + 230 < x1 else ((t - 300) / 2 + 230 - x1) / x2 * z3
e5 = ((x1 - (t - 70)) / x1 * 0.0035) if t - 70 < x1 else ((t - 70) - x1) / x2 * z3

f1 = min(e1 * e, fy2)
f2 = min(e2 * e, fy1)
f3_1 = min(e3 * e, fy1)
f3_2 = min(e3 * e, fy2)
f4 = min(e4 * e, fy1)
f5 = min(e5 * e, fy2)

if x1 > ((t + 300) / 2):
    C1 = alpha1 * phi_c * 300 * 300 * beta1 * (fc1 - fc2)
    C2 = alpha2 * phi_c * beta2 * x1 * fc2 * t
else:
    C1 = alpha1 * 300 * (x1 - ((t - 300) / 2)) * (fc1 - fc2) * beta1 * phi_c
    C2 = alpha2 * x1 * beta2 * phi_c * fc2 * t

t1 = 3 * pie * (mill * mill) * (phi_s * f1 - alpha2 * phi_c * fc2)
t2 = 3 * pie * (mill * mill) * (phi_s * f2 - alpha1 * phi_c * fc1)
t3_1 = 2 * pie * (mill * mill) * (phi_s * f3_1 - alpha1 * phi_c * fc1)
t3_2 = 2 * pie * (mill * mill) * (phi_s * f3_2 - alpha2 * phi_c * fc2)

if ((t - 300) / 2) + 230 < x1:
    t4 = 3 * pie * (mill * mill) * (phi_s * f4 - alpha1 * phi_c * fc1)
else:
    t4 = -3 * pie * (mill * mill) * (phi_s * f4)

if (t - 70) < x1:
    t5 = 3 * pie * (mill * mill) * (phi_s * f5 - alpha2 * phi_c * fc2)
else:
    t5 = -3 * pie * (mill * mill) * (phi_s * f5)

Nr3 = (C1 + C2 + t1 + t2 + t3_1 + t3_2 + t4 + t5) / 1000
if x1 > ((t + 300) / 2):
    Mr3 = ((C2 * (t / 2 - beta2 * (x1 / 2))) + (C1 * (t / 2 - (150 * beta1) - ((t - 300) / 2))) + (
            t1 * (t / 2 - 70)) + (t2 * 80) - (t4 * 80) - (t5 * (t / 2 - 70))) * (10 ^ (-6))
else:
    Mr3 = ((C2 * (t / 2 - beta2 * (x1 / 2))) + (
            C1 * (t / 2 - (x1 - ((t - 300) / 2)) - (beta1 * (x1 - ((t - 300) / 2))))) + (t1 * (t / 2 - 70)) + (
                   t2 * 80) - (t4 * 80) - (t5 * (t / 2 - 70))) * (10 ^ (-6))
k3 = Nr3
j3 = Mr3

# noghteye 4
z4 = 0.0035
x1 = t * 0.0035 / (0.0035 + z4)
x2 = t - x1

e1 = (x1 - 70) / x1 * 0.0035
e2 = (x1 - (t - 300) / 2 - 70) / x1 * 0.0035
e3 = (x1 - (t / 2)) / x1 * 0.0035
e4 = (x1 - (t - 300) / 2 - 230) / x1 * 0.0035 if (t - 300) / 2 + 230 < x1 else ((t - 300) / 2 + 230 - x1) / x2 * z4
e5 = ((x1 - (t - 70)) / x1 * 0.0035) if t - 70 < x1 else ((t - 70) - x1) / x2 * z4

f1 = min(e1 * e, fy2)
f2 = min(e2 * e, fy1)
f3_1 = min(e3 * e, fy1)
f3_2 = min(e3 * e, fy2)
f4 = min(e4 * e, fy1)
f5 = min(e5 * e, fy2)

C1 = alpha1 * 300 * (x1 - ((t - 300) / 2)) * (fc1 - fc2) * beta1 * phi_c
C2 = alpha2 * x1 * beta2 * phi_c * fc2 * t

t1 = 3 * pie * (mill * mill) * (phi_s * f1 - alpha2 * phi_c * fc2)
t2 = 3 * pie * (mill * mill) * (phi_s * f2 - alpha1 * phi_c * fc1)
t3_1 = 2 * pie * (mill * mill) * (phi_s * f3_1 - alpha1 * phi_c * fc1)
t3_2 = 2 * pie * (mill * mill) * (phi_s * f3_2 - alpha2 * phi_c * fc2)
t4 = -3 * pie * (mill * mill) * (phi_s * f4)
t5 = -3 * pie * (mill * mill) * (phi_s * f5)

Nr4 = 0
Mr4 = (((C2 * (t / 2 - beta2 * (x1 / 2))) + (C1 * 150 * beta1) + (2 * t1 * (t / 2 - 70)) + (2 * t2 * 80))) * (10 ^ (-6))
k4 = Nr4
j4 = Mr4

print(k1, " ", j1)
print(k2, " ", j2)
print(k3, " ", j3)
print(k4, " ", j4)
