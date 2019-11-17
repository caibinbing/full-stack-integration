# Transaction
事务

+ DISCARD

取消事务，放弃执行事务块内的所有命令。

+ EXEC

执行所有事务块内的命令。返回nil表示事务提交失败。

+ MULTI

标记一个事务块的开始。

+ UNWATCH

取消 WATCH 命令对所有 key 的监视。

+ WATCH key

监视一个(或多个) key ，如果在事务执行之前这个(或这些) key 被其他命令所改动，那么事务将被打断。

+ 语法错误怎么办？

exec之前存在语法错误，redis放弃事务的执行。

exec之前存在语句场景错误，例如lpop操作string，redis会执行完其他命令。

+ 何时取消key的watch？

watch可以多次调用。直到exec位置。不管事务是否成功执行，对所有键的watch都会取消。

当客户端断开连接，该客户端对键的watch也会被取消。

unwatch命令可以手动取消对所有键的监控。