list1 = [123]
list2 = [234]
print(list1 > list2)	#false

list1 = [123 , 456]
list2 = [234 , 123]
print(list1 > list2)	#false

list3 = [123 , 456]
print((list1 < list2) and (list1 == list3))	#true

list4 = list1 + list2
print(list4)	#[123, 456, 234, 123]

#error: list1 + 'anything'

print(list3 * 3) #[123, 456, 123, 456, 123, 456],also list3 *= 3

print(123 in list3) #True , also not in

list5 = [123 , ['apple' , 'banana'] , 456]
print('apple' in list5)	#false
print('apple' in list5[1]) #true

print(list3.count(123))	#1
print(list3.index(456))	#1

list3.reverse()	#if print(list3.reverse()) output None
print(list3)

list6 = [4 , 2 , 5 , 1 , 9 , 23 , 32 , 0]
list6.sort()
print(list6)
list6.sort(reverse=True)
print(list6)

