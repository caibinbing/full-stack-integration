#Change Request
#1 not only once
#2 guess 3 times
#3 random result number
#4 input validation

import random
result = random.randint(1,10)
#can not use printf
#end with nothing
print('-------A simple word game-----------')
temp = input("input a number: ")

if not isinstance(temp,int):
	print("input error !")
else:	
	guess = int(temp)
	count = 1
#Tab is important!
#: is important!
	while guess != result and count < 3 :
		if guess > result :
			print("Bigger")
		if guess < result :
			print("smaller")
		temp = input("input a number: ")
		guess = int(temp)
		count += 1
	if guess != result and count >= 3 :
		print("WTF")
	elif  guess == result :
		print("Excellent")
print("Game over")
