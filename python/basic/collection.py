num = {}
print(type(num))    #<class 'dict'>

num1 = {1,2,3,4,5}
print(type(num1))    #<class 'set'>

num2 = {1,2,3,4,5,5,4,3,2}
print(num2) #{1, 2, 3, 4, 5};distinct

set1 = set([1,2,3,4,5,5])
print(set1) #{1, 2, 3, 4, 5}

temp = {1,2,3,4,5,0}
num3 = list(set(temp))
print(temp) #{0, 1, 2, 3, 4, 5};will sort

temp.add(6)
print(temp) #{0, 1, 2, 3, 4, 5, 6}

temp.remove(5)
print(temp) #{0, 1, 2, 3, 4, 6}

num3 = frozenset([1,2,3,4,5])
#num3.add(6)    #AttributeError: 'frozenset' object has no attribute 'add'
