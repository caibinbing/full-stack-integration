member = ['Bob' , 'Nick' , 'David' , 'Milo']
print("Origin : ")
print(member)

mix = [1 , 'Bob' , 3.14 , [1 , 2 , 3 ]]
print("mix : ")
print(mix)

empty = []
print("empty : ")
print(empty)

#append() , takes exactly one argument
member.append('Denny')
print("member.append('Denny') : ")
print(member)

#extend() , takes exactly one argument:a list,can add several arg at once
member.extend(['Candy' , 'Nancy'])
print("member.extend(['Candy' , 'Nancy']) : ")
print(member)

print("member len : ".join(str(len(member))))

#insert() , insert at , [list] starts at index 0
member.insert(1 , 'Dog')
print("member insert at index 1 : ")
print(member)

#change elements
temp = member[0]
member[0] = member[1]
member[1] = temp
print("change elements : " , member)

#delete elements
member.remove("Nancy") #must know elements' detail
print("remove 'Nancy' : ", member)

del member[1] # if del member ,delete all.
print("del member[1] : ", member)

member.pop() # can add index , like pop(1)
print("after pop top one : " , member)

#print index 1 , 2 , just a copy
#member[:3]
#member[1:]
#member[:]
#member[3:1:-1] , step -1
print(member[1:3])


