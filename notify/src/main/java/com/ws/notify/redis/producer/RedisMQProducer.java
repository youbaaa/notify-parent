/*
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.ws.notify.redis.producer;

import com.ws.notify.common.redis.jedis.JedisTemplate;
import com.ws.notify.redis.producer.annotation.RedisProducer;
import com.ws.notify.redis.support.MsgSendCallback;
import com.ws.notify.redis.support.MsgSendResult;
import com.ws.notify.redis.support.YesNo;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

/**
 * @author huang.zhangh
 * @version QueueProducer.java, v 0.1 2021-02-25 上午9:02
 */
@RedisProducer(value = "RedisMQProducer")
public class RedisMQProducer extends Producer {
    @Resource
    JedisTemplate jedisTemplate;

    @Override
    public MsgSendResult send(String topic, String msg, boolean async, MsgSendCallback sendCallback) {
        if (!StringUtils.hasLength(topic) || !StringUtils.hasLength(msg)) {
            throw new IllegalArgumentException("参数错误!");
        }
        MsgSendResult msgSendResult = new MsgSendResult();
        if (async && sendCallback != null) {
            CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> jedisTemplate.lpush(topic, msg))
                    .handle((aLong, throwable) -> {
                        if (throwable == null) {
                            msgSendResult.setYesNo(YesNo.yes);
                            msgSendResult.setTopic(topic);
                            msgSendResult.setMessageId(String.valueOf(aLong));
                            sendCallback.onSuccess(msgSendResult);
                        } else {
                            sendCallback.onException(throwable);
                        }
                        return null;
                    });
        } else {
            long lpush = jedisTemplate.lpush(topic, msg);
            msgSendResult.setTopic(topic);
            msgSendResult.setMessageId(String.valueOf(lpush));
            msgSendResult.setYesNo(YesNo.yes);
        }

        return msgSendResult;
    }
}