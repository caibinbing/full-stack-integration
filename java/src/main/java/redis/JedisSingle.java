package main.java.redis;

import redis.clients.jedis.Jedis;

public class JedisSingle {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.1.1", 6379);
        jedis.set("name", "milo");
        String name = jedis.get("name");
        System.out.println("name = " + name);
        jedis.close();
    }
}
