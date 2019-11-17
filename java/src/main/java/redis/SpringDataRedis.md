# Spring Data Redis

+ 使用RedisTemplate操作

> redisTemplate.boundHashOps(Object key)
> redisTemplate.boundListOps(Object key)
> redisTemplate.boundSetOps(Object key)
> redisTemplate.boundValueOps(Object key).set(Object value)
> redisTemplate.boundZSetOps(Object key)
> redisTemplate.delete(Object key)

+ 删除i个指定的元素

redisTemplate.boundListOps(Object key).remove(i, value)

+ 初始化Hash

redisTemplate.boundHashOps(Object key).put(k, v)

+ 删除指定元素

redisTemplate.boundHashOps(Object key).delete(k1, k2, k3)

+ ZSet添加元素时，需要设置元素的权重

+ ZSet添加一个有序结合
```java
public class TestZSet{
    public void addSet(){
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<Object>("zset-5", 9.1);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<Object>("zset-6", 9.2);
        Set<ZSetOperations.TypeTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        redisTemplate.boundZSetOps("zset1").add(tuples);
    }
}
```

+ ZSet获取排位

redisTemplate.boundZSetOps(Object key).rank(k)