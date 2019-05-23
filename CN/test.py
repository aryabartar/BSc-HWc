
f = open("funnyName.py", "r")
text = f.read()

array = text.split("\n")
# print(array)
array1= []
for word in array:
    w = word[4:]
    array1.append(w)
print (array1)