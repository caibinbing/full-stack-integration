package main.java.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RedisSubscribe extends JedisPubSub {
    //收到消息时回调的方法
    @Override
    public void onMessage(String channel, String message) {
        super.onMessage(channel, message);
    }

    public static void main(String[] args) {
        //创建Jedis
        Jedis jedis = new Jedis("192.168.1.1", 6379);
        //创建redisSubscribe对象
        RedisSubscribe redisSubscribe = new RedisSubscribe();
        //订阅
        jedis.subscribe(redisSubscribe, "cctv");
    }

}
