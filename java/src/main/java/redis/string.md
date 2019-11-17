# String

+ set key value

设置值

+ get key

获取值

+ set key value ex 100

设置值，包含过期时间

+ ttl key

查看过期时间

+ keys *

查看所有key

+ mset QQ 12345678 SEX female

设置多个值

+ setnx

设置值，如果不存在，返回1，如果存在，返回0。不覆盖。

+ setex key 100 value

设置值，带过期时间

+ setrange string range value

替换字符串

+ getset key value

先获取，再修改值

+ incr

自增

+ decr

自减

+ append key value

扩充字符串

+ strlen key

获取字符串长度