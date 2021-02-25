/*
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.ws.notify.common.redis.util;

/**
 * @author huang.zhangh
 * @version RedisServeEnum.java, v 0.1 2021-02-24 下午2:15
 */
public enum RedisServeEnum {
    /**
     * 集群模式, 多master多slave
     */
    cluster,
    /**
     * 单机模式
     */
    standalone,
    /**
     * 哨兵模式, 单master不少于一个slave
     */
    sentinel;

    public static RedisServeEnum parseByType(String type) {
        for (RedisServeEnum redisServeEnum : RedisServeEnum.values()) {
            if (redisServeEnum.name().equals(type)) {
                return redisServeEnum;
            }
        }
        return null;
    }
}