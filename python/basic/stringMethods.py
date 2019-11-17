str = "python"
print(str.capitalize()) #Python

str2 = "I LOVE python python"
print(str2.casefold())  #i love python python

print(str2.center(40))  #"             I LOVE python python              "

print(str2.count("python")) #2

print(str2.endswith("th"))  #False

print(str2.endswith("on"))  #True

str3 = "I\tlove\tpython"

print(str3)                     #I	love	python

print(str3.expandtabs(16))      #I               love            python

print(str3.find("abc")) #-1:not found

print(str3.find("i"))   #-1:not found

print(str3.find("I"))   #0:at index(0)

#use str.find() instead of str.index(sub).index(sub) would throw exception.

str4 = 'Python'

print(str4.istitle())   #True

print(str4.join("12345"))   #1Python2Python3Python4Python5

print(str2.partition(" "))  #('I', ' ', 'LOVE python python');(pre_sub,sub,fol_sub)

print(str2.split())     #['I', 'LOVE', 'python', 'python']

print(str2.split(" "))  #['I', 'LOVE', 'python', 'python']

print(str2.translate(str.maketrans("y","i")))   #I LOVE pithon pithon

print(str.maketrans("y","i"))   #{121: 105};ASCII
