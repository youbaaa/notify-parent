/*
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.ws.notify.common.redis.conf;

import com.ws.notify.common.redis.jedis.JedisTemplate;
import com.ws.notify.common.redis.jedis.impl.JedisClusterTemplate;
import com.ws.notify.common.redis.jedis.impl.JedisSentinelTemplate;
import com.ws.notify.common.redis.jedis.impl.JedisStandaloneTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisSentinelPool;

import javax.annotation.Resource;

/**
 * @author huang.zhangh
 * @version JedisTemplateCOnf.java, v 0.1 2021-02-24 下午3:05
 */
@Configuration
public class JedisTemplateConf {
    @Resource(name = "jedisPool")
    Object jedisPool;

    @DependsOn("jedisPool")
    @Bean(value = "jedisTemplate", destroyMethod = "destroy")
    public JedisTemplate jedisTemplate() {
        if (JedisPoolConf.redisProperties == null) {
            throw new NullPointerException("JedisPoolConf.redisConf is null!");
        }
        JedisTemplate jedisTemplate = null;
        switch (JedisPoolConf.redisProperties.getServeType()) {
            case cluster:
                jedisTemplate = new JedisClusterTemplate();
                ((JedisClusterTemplate) jedisTemplate).setJedisCluster((JedisCluster) jedisPool);
                break;
            case standalone:
                jedisTemplate = new JedisStandaloneTemplate();
                ((JedisStandaloneTemplate) jedisTemplate).setJedisPool((JedisPool) jedisPool);
                break;
            case sentinel:
                jedisTemplate = new JedisSentinelTemplate();
                ((JedisSentinelTemplate) jedisTemplate).setJedisPool((JedisSentinelPool) jedisPool);
                break;
            default:
                break;

        }
        return jedisTemplate;
    }

}