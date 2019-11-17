# Sorted Set
有序集合

+ zadd key score member

将一个或者多个member元素及其score值加入到有序集key中。score越大排越后。

+ zrank key member

返回有序集key中成员member的排名。其中有序集成员按score值从小到大顺序排列。

+  ZRANGE key start stop （withscores）

返回有序集指定区间的成员。

+ zcard key

返回有序集key的基数。

+ zrem key member

移除有序集key中的一个或者多个成员，不存在的成员将被忽略。