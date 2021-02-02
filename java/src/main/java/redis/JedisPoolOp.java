package main.java.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 使用Jedis连接池方式操作
 */
public class JedisPoolOp {
    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(30);
        config.setMaxIdle(10);

        JedisPool jedisPool = new JedisPool(config, "192.168.1.1", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.set("name", "caibinbing");
        String name = jedis.get("name");
        System.out.println("name = " + name);
        jedis.close();
        jedisPool.close();
    }
}
