/*
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.ws.notify.redis.consumer;

import com.ws.notify.common.redis.jedis.JedisTemplate;
import com.ws.notify.redis.consumer.annotation.RedisConsumer;

import javax.annotation.Resource;

/**
 * @author huang.zhangh
 * @version QueueConsumer.java, v 0.1 2021-02-25 上午9:33
 */
@RedisConsumer(value = "RedisMQConsumer")
public class RedisMQConsumer extends Consumer {
    @Resource
    private JedisTemplate jedisTemplate;

    @Override
    public String getMessage(String topic) {
        return jedisTemplate.rpop(topic);
    }

    @Override
    public void retry(String topic, String message) {
        jedisTemplate.lpush(topic, message);
    }
}