# Hash

```
hashname{
    id:01,
    name:aa,
    phone:bb,
}
```

+ hset key filed value

设置值将hash表key中的域值设为value，旧值会覆盖

+ hget key filed

取值

+ hmset key field value

同时将多个field-value对设置到hash表key中

+ hmget key filed1 filed2 filed3

批量取值

+ hsetnx key filed value

存在时操作无效，不存在才创建值

+ expire key 100

设置过期时间

+ hincrby key filed increment

自增，递减就设置increment负值

+ hexists key field

是否存在，存在返回1，不存在返回0

+ hdel key field

删除field

+ hlen key

返回所有key数量

+ hkeys key

返回key的具体内容

+ hvals key

返回所有value

+ hgetall key

返回所有field和value