result = lambda x , y : x * y

print(result(3, 5 ))    #15

filt = filter(None ,[1,0,False,True])

print(list(filt))   #[1, True]


def odd(x):
    return x % 2

temp = range(10)
show = filter(odd, temp)
print(list(show))   #[1, 3, 5, 7, 9]

print(list(filter(lambda x : x%2,range(10))))   #[1, 3, 5, 7, 9]

print(list(map(lambda x : x * 2 , range(10))))  #[0, 2, 4, 6, 8, 10, 12, 14, 16, 18]
