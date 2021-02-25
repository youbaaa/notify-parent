/*
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.ws.notify.common.redis.conf;

import com.ws.notify.common.redis.util.RedisServeEnum;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;

import java.util.HashSet;
import java.util.Set;

/**
 * @author huang.zhangh
 * @version RedisProperties.java, v 0.1 2021-02-24 下午1:39
 */
public class RedisProperties {

    private RedisServeEnum serveType = RedisServeEnum.cluster;

    /**
     * 节点master
     */
    private String master;

    /**
     * 格式
     * 127.0.0.1:6379,127.0.0.1:6378,...
     */
    private String address;

    /**
     * 数据库序号，默认0
     */
    private int dbIndex = 0;

    /**
     * 密码
     */
    private String password;

    private int maxTotal = 8;
    private int maxIdle = 8;
    private int minIdle = 4;


    public RedisServeEnum getServeType() {
        return serveType;
    }

    public void setServeType(RedisServeEnum serveType) {
        this.serveType = serveType;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public HostAndPort getNode() throws Exception {
        String[] hostAndPorts = getAddrIp();
        if (hostAndPorts.length != 1) {
            throw new Exception("redis node more than 1");
        }
        String[] hostAndPortStrs = hostAndPorts[0].split(":");
        return new HostAndPort(hostAndPortStrs[0], Integer.parseInt(hostAndPortStrs[1]));

    }

    public String[] getAddrIp() {
        if (getAddress() == null || "".equals(getAddress())) {
            return new String[0];
        }
        return getAddress().split(",");
    }

    public GenericObjectPoolConfig getPoolConfig() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        if (config.getMaxTotal() > 0) {
            config.setMaxTotal(getMaxTotal());
        }

        if (config.getMaxIdle() > 0) {
            config.setMaxIdle(getMaxIdle());
        }

        if (config.getMinIdle() > 0) {
            config.setMinIdle(getMinIdle());
        }
        return config;
    }

    public String getHost() throws Exception {
        String[] hostAndPorts = getAddrIp();
        if (hostAndPorts.length != 1) {
            throw new Exception("redis node more than 1");
        }
        String[] hostAndPortArr = hostAndPorts[0].split(":");
        return hostAndPortArr[0];

    }

    public int getPort() throws Exception {
        String[] hostAndPorts = getAddrIp();
        if (hostAndPorts.length != 1) {
            throw new Exception("redis node more than 1");
        }
        String[] hostAndPortArr = hostAndPorts[0].split(":");
        return Integer.parseInt(hostAndPortArr[1]);

    }

    public Iterable<? extends HostAndPort> getNodes() throws Exception {
        String[] hostAndPorts = getAddrIp();
        if (hostAndPorts.length < 1) {
            throw new Exception("redis node more than 1");
        }
        Set<HostAndPort> items = new HashSet<>();
        for (String ipPortItem : hostAndPorts) {
            String[] ipPort = ipPortItem.split(":");
            items.add(new HostAndPort(ipPort[0], Integer.parseInt(ipPort[1])));
        }
        return items;
    }

    public Set<String> getSentinels() throws Exception {
        Set<String> sentinels = new HashSet<>();
        for (HostAndPort node : getNodes()) {
            sentinels.add(node.toString());
        }
        return sentinels;
    }
}