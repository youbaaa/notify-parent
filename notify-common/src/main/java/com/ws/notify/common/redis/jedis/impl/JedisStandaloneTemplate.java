package com.ws.notify.common.redis.jedis.impl;


import com.ws.notify.common.redis.jedis.JedisTemplate;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisStandaloneTemplate implements JedisTemplate {

    private JedisPool jedisPool;

    @Override
    public void destroy() {
        jedisPool.close();
    }

    @Override
    public long del(String... keys) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.del(keys);
        }
    }

    @Override
    public boolean exists(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.exists(key);
        }
    }

    @Override
    public long expire(String key, int seconds) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.expire(key, seconds);
        }
    }

    @Override
    public Set<String> keys(String pattern) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys(pattern);
        }
    }

    @Override
    public String set(String key, String val) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.set(key, val);
        }
    }

    @Override
    public boolean setbit(String key, long offset, boolean val) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.setbit(key, offset, val);
        }
    }

    @Override
    public boolean setbit(String key, long offset, String val) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.setbit(key, offset, val);
        }
    }

    @Override
    public Long bitcount(String key, long start, long end) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.bitcount(key, start, end);
        }
    }

    @Override
    public Long bitcount(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.bitcount(key);
        }
    }

    @Override
    public Long bitand(String destKey, String... srcKey) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.bitop(BitOP.AND, destKey, srcKey);
        }
    }

    @Override
    public Long bitor(String destKey, String... srcKey) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.bitop(BitOP.OR, destKey, srcKey);
        }
    }

    @Override
    public Long bitxor(String destKey, String... srcKey) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.bitop(BitOP.XOR, destKey, srcKey);
        }
    }

    @Override
    public Long bitnot(String destKey, String srcKey) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.bitop(BitOP.NOT, destKey, srcKey);
        }
    }

    @Override
    public boolean setIfAbsent(String key, String value, long timeout) {
        try (Jedis jedis = jedisPool.getResource()) {
            SetParams setParam = new SetParams();
            setParam.ex((int) timeout);
            setParam.nx();
            String res = jedis.set(key, value, setParam);
            return "OK".equals(res);
        }
    }

    @Override
    public long setNx(String key, String val) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.setnx(key, val);
        }
    }

    @Override
    public String setEx(String key, String val, int seconds) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.setex(key, seconds, val);
        }
    }

    @Override
    public String get(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        }
    }

    @Override
    public List<String> mget(String... keys) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.mget(keys);
        }
    }

    @Override
    public long increBy(String key, long increment) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.incrBy(key, increment);
        }
    }

    @Override
    public long decrBy(String key, long increment) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.decrBy(key, increment);
        }
    }



    @Override
    public long hdel(String hashkey, String... keys) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hdel(hashkey, keys);
        }
    }

    @Override
    public boolean hexists(String hashkey, String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hexists(hashkey, key);
        }
    }

    @Override
    public String hget(String hashkey, String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hget(hashkey, key);
        }
    }

    @Override
    public Map<String, String> hgetAll(String hashkey) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hgetAll(hashkey);
        }
    }

    @Override
    public Long hincrby(String hashkey, String key, long increment) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hincrBy(hashkey, key, increment);
        }
    }

    @Override
    public Set<String> hkeys(String hashkey) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hkeys(hashkey);
        }
    }

    @Override
    public long hlen(String hashkey) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hlen(hashkey);
        }
    }

    @Override
    public long hsetnx(String hashkey, String key, String val) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hsetnx(hashkey, key, val);
        }
    }

    @Override
    public long hset(String hashkey, String key, String val) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hset(hashkey, key, val);
        }
    }

    @Override
    public long hdel(String hashkey, String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hdel(hashkey, key);
        }
    }

    @Override
    public String hmset(String hashkey, Map<String, String> mapdata) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hmset(hashkey, mapdata);
        }
    }

    @Override
    public long sadd(String hashkey, String... members) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.sadd(hashkey, members);
        }
    }

    @Override
    public long scard(String hashkey) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.scard(hashkey);
        }
    }

    @Override
    public Set<String> smembers(String hashkey) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.smembers(hashkey);
        }
    }

    @Override
    public long srem(String hashkey, String... members) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.srem(hashkey);
        }
    }

    @Override
    public String spop(String hashkey) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.spop(hashkey);
        }
    }

    @Override
    public long zadd(String hashkey, String member, double score) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.zadd(hashkey, score, member);
        }
    }

    @Override
    public long zadd(String hashkey, Map<String, Double> scoreMembers) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.zadd(hashkey, scoreMembers);
        }
    }

    @Override
    public long zcard(String hashkey) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.zcard(hashkey);
        }
    }

    @Override
    public long zcount(String hashkey, double minScore, double maxScore) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.zcount(hashkey, minScore, maxScore);
        }
    }

    @Override
    public double zincrby(String hashkey, String key, double increment) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.zincrby(hashkey, increment, key);
        }
    }

    @Override
    public Set<String> zrange(String hashkey, long start, long stop) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.zrange(hashkey, start, stop);
        }
    }

    @Override
    public Set<String> zrangeByScore(String hashkey, double minScore, double maxScore) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.zrangeByScore(hashkey, minScore, maxScore);
        }
    }

    @Override
    public long zrank(String hashkey, String member) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.zrank(hashkey, member);
        }
    }

    @Override
    public Long zRemove(String key, String... values) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.zrem(key, values);
        }
    }

    @Override
    public long llen(String lkey) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.llen(lkey);
        }
    }

    @Override
    public String lindex(String lkey, long index) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.lindex(lkey, index);
        }
    }

    @Override
    public long lpush(String lkey, String... members) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.lpush(lkey, members);
        }
    }

    @Override
    public String lpop(String lkey) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.lpop(lkey);
        }
    }

    @Override
    public long rpush(String lkey, String... members) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.rpush(lkey, members);
        }
    }

    @Override
    public String rpop(String lkey) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.rpop(lkey);
        }
    }

    @Override
    public List<String> blpop(String lkey, int timeout) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.blpop(timeout, lkey);
        }
    }

    @Override
    public List<String> brpop(String lkey, int timeout) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.brpop(timeout, lkey);
        }
    }

    @Override
    public String lset(String lkey, long index, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.lset(lkey, index, value);
        }
    }

    @Override
    public List<String> lrange(String lkey, long start, long stop) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.lrange(lkey, start, stop);
        }
    }

    @Override
    public long lrem(String lkey, long count, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.lrem(lkey, count, value);
        }
    }
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}