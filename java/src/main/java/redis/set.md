# Set

无序集合，不重复

+ sadd key member

将一个或者多个member元素加入到集合key中，集合中已存在的member元素被忽略

+ smember key

查看集合

+ scard key

返回集合key的基数（集合中元素的数量）

+ smove source destination member

将member元素从source集合移动到destination集合

+ srem key member

移除集合key中的一个或者多个member元素，不存在的member元素会被忽略

+ sdiff key
s
返回一个集合的全部成员，该集合是所有给定集合之间的差集。

+ sinsert key

返回一个集合的全部成员，该集合是所有给定集合的交集。

+ spop key

移除并返回集合中的一个随机元素。