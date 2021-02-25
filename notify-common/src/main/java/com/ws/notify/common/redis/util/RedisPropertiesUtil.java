/*
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.ws.notify.common.redis.util;

import com.ws.notify.common.redis.conf.RedisProperties;
import com.ws.notify.common.util.PropertiesUtil;
import org.springframework.util.StringUtils;

import java.util.Properties;

/**
 * @author huang.zhangh
 * @version RedisPropertiesUtil.java, v 0.1 2021-02-24 下午1:39
 */
public class RedisPropertiesUtil {

    public static RedisProperties parseConf(String path) throws Exception {
        Properties properties = PropertiesUtil.parseProperties(path);

        String type = properties.getProperty("ws.cache.redis.type");
        String master = properties.getProperty("ws.cache.redis.master");
        String password = properties.getProperty("ws.cache.redis.password");
        String address = properties.getProperty("ws.cache.redis.address");

        int dbIndex = getInt(properties, "ws.cache.redis.dbIndex", 0);
        int maxTotal = getInt(properties, "ws.cache.redis.maxTotal", 8);
        int maxIdle = getInt(properties, "ws.cache.redis.maxIdle", 8);
        int minIdle = getInt(properties, "ws.cache.redis.minIdle", 0);
        RedisProperties redisConf = new RedisProperties();
        redisConf.setMaster(master);
        redisConf.setAddress(address);
        redisConf.setServeType(RedisServeEnum.parseByType(type));
        redisConf.setPassword(password);
        redisConf.setDbIndex(dbIndex);

        redisConf.setMaxTotal(maxTotal);
        redisConf.setMaxIdle(maxIdle);
        redisConf.setMinIdle(minIdle);
        if (!StringUtils.hasLength(address)) {
            throw new Exception("address is empty");
        }
        return redisConf;
    }

    private static int getInt(Properties properties, String key, int defaultVal) {
        String proVal = properties.getProperty(key);
        if (proVal != null) {
            return Integer.parseInt(proVal);
        }
        return defaultVal;
    }

}