# List

+ lpush key value

将一个或者多个value插入到列表key的表头，如果key不存在，会自动创建

> lpush a b c 输出： c b a

+ lrange key start stop

返回列表key中指定区间内的的元素，包含stop。范围可以为负数，表示倒数

+ lpop

移除并返回列表key的头元素，弹栈

+ llen

查询长度

+ lset key index value

将列表key下表为index的元素值设置成value

+ lindex key index

返回列表key中，下表为index的元素

+ linsert key before|after pivot value

将value插入到列表key中，位于值pivot之前或者之后