package com.yjy.read.util;

import redis.clients.jedis.Jedis;

public class JedisUtil {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("123456");
        jedis.setex("jedis", 60,"hello jedis");
        System.out.println(jedis.get("jedis"));

        jedis.close();
    }
}
