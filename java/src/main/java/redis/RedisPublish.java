package main.java.redis;

import redis.clients.jedis.Jedis;

public class RedisPublish {
    public static void main(String[] args) {
        //发布者不需要回调，直接发布消息即可
        Jedis jedis = new Jedis("192.168.1.1", 6379);
        jedis.publish("cctv", "abc");
    }

}
