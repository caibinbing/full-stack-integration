str = "{0} love {1} {2}".format("I" , "python" , "now")

print(str)  #I love python now


str1 = "{a} love {b} {c}".format(a="I" , b="python" , c="now")

print(str1) #I love python now

#if "{a} love {b} {0}".format(a="I" , b="python" , "now")
#SyntaxError:non-keyword arg after keyword arg

print("{{0}}".format("not print"))  #{0}

print("{0:.1f}{1}".format(27.658 , "GB"))   #27.7GB
