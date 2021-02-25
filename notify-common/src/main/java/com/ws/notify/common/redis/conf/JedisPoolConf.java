/*
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.ws.notify.common.redis.conf;

import com.ws.notify.common.redis.util.RedisPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisSentinelPool;

/**
 * @author huang.zhangh
 * @version JedisPoolConf.java, v 0.1 2021-02-24 下午1:44
 */
@Configuration
public class JedisPoolConf {
    private static Logger logger = LoggerFactory.getLogger(JedisPoolConf.class);

    @Value("${ws.redis.conf}")
    private String redisConf;

    static RedisProperties redisProperties;

    @Bean(value = {"jedisPool"})
    public Object jedisPool() throws Exception {
        if (!StringUtils.hasLength(redisConf)) {
            throw new IllegalArgumentException("缺少配置参数:ws.redis.conf=redis-xxx.properties");
        }
        redisProperties = RedisPropertiesUtil.parseConf(redisConf);
        String password = redisProperties.getPassword();
        switch (redisProperties.getServeType()) {
            case cluster:
                if (!StringUtils.hasLength(password)) {
                    return new JedisCluster(redisProperties.getNode(), redisProperties.getPoolConfig());
                }
                return new JedisCluster(
                        redisProperties.getNode(),
                        20000,
                        2000,
                        3,
                        password,
                        redisProperties.getPoolConfig());

            case standalone:
                if (!StringUtils.hasLength(password)) {
                    return new JedisPool(
                            redisProperties.getPoolConfig(),
                            redisProperties.getHost(),
                            redisProperties.getPort(),
                            redisProperties.getDbIndex());
                }
                return new JedisPool(
                        redisProperties.getPoolConfig(),
                        redisProperties.getHost(),
                        redisProperties.getPort(),
                        20000,
                        password,
                        redisProperties.getDbIndex());
            case sentinel:

                if (!StringUtils.hasLength(password)) {
                    return new JedisSentinelPool(
                            redisProperties.getMaster(),
                            redisProperties.getSentinels());
                }
                return new JedisSentinelPool(redisProperties.getMaster(),
                        redisProperties.getSentinels(),
                        password);
            default:
                throw new Exception("ws.cache.redis.type is error, example:cluster|standalone|sentinel");
        }
    }

}