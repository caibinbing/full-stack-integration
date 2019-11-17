# Jedis

+ 单实例连接

```java
    public class JedisSingle{
        public static void main(String[] args){
            //设置IP地址和端口
            Jedis jedis = new Jedis("192.168.1.1",6379);
            //设置数据
            jedis.set("name","milo");
            //获取数据
            String name = jedis.get("name");
            System.out.println("name = " + name);
            jedis.close();
        }
    }
```

+ 官方推荐：连接池的连接方式

```java
    public class JedisPoolOp{
        public static void main(String[] args){
            //获取连接池配置对象，设置配置项
            JedisPoolConfig config = new JedisPoolConfig();
            //设置最大连接数
            config.setMaxTotal(30);
            //设置最大空闲
            config.setMaxIdle(10);
            //获取连接池
            JedisPool jedisPool = new JedisPool(config,"192.168.1.1",6379);
            Jedis jedis = null;
            try{
                jedis = jedisPool.getResource();
                //设置数据
                jedis.set("name","milo");
                String name = jedis.get("name");
                System.out.println("name = " + name);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                if(jedis != null){
                    jedis.close();
                }
                jedisPool.close();
            }
        }
    }
```

+ lpush可以同时添加多个对象。

+ lrem和ltrim都可以删除。

+ lset可以修改指定位置的对象。

+ lindex获取指定位置的对象。

+ sort可以进行排序，升序。不改变数据库内容。

+ smembers可以查看set中所有元素。

+ srem删除指定元素，spop随机删除一个元素。

+ hkeys返回Set集合，hvals返回List集合。

