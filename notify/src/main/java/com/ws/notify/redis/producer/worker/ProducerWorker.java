/*
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.ws.notify.redis.producer.worker;

import com.ws.notify.redis.producer.Producer;
import com.ws.notify.redis.producer.annotation.ToQueue;
import com.ws.notify.redis.support.MsgSendResult;
import com.ws.notify.redis.support.YesNo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author huang.zhangh
 * @version ProducerWorker.java, v 0.1 2021-02-25 下午2:25
 */
@Aspect
public class ProducerWorker {

    private Producer producer;

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    @Around("@annotation(toQueue)")
    public MsgSendResult around(ProceedingJoinPoint point, ToQueue toQueue) {
        MsgSendResult send = new MsgSendResult();
        try {
            Object[] content = point.getArgs();
            for(Object obj:content){
                System.out.println(obj);
            }
            String topic = toQueue.topic();
            boolean async = toQueue.async();
            send = producer.send(topic, content[0].toString(), async, null);
        } catch (Throwable throwable) {
            send.setYesNo(YesNo.no);
            send.setException(throwable);
        }
        return send;
    }
}