dict1 = {1:"I" , 2:"Love" , 3:"Python"}

print(dict1[3]) #Python

dict2 = dict(((1,"I"),(2,"Love"),(3,"Python"),(4,"Now")))

print(dict2)    #{1: 'I', 2: 'Love', 3: 'Python', 4: 'Now'}

#dict3 = dict(1="I",2="Love",3="Python") #keyword can't be an expression

print(dict2.fromkeys((1,2,3)))  #{1: None, 2: None, 3: None}

#{1: ('one', 'two', 'three'), 2: ('one', 'two', 'three'), 3: ('one', 'two', 'three')}
print(dict2.fromkeys((1,2,3),("one","two","three")))

print(dict2.fromkeys(range(5),"good"))  #{0: 'good', 1: 'good', 2: 'good', 3: 'good', 4: 'good'}

for eachKey in dict2.values():
    print(eachKey)  #print(eachItem)

#dict2.clear()
