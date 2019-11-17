# Publish and Subscribe
发布订阅命令

+ PSUBSCRIBE pattern

订阅一个或多个符合给定模式的频道。

+ PUBSUB subcommand argument

查看订阅与发布系统状态。

+ PUBLISH channel message

将信息发送到指定的频道。

+ PUNSUBSCRIBE pattern

退订所有给定模式的频道。

+ SUBSCRIBE channel

订阅给定的一个或多个频道的信息。

+ UNSUBSCRIBE channel

指退订给定的频道。

+ Jedis实现订阅发布

通过继承JedisPubSub类，重写回调方法。