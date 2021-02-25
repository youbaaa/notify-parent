/*
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.ws.notify.redis.consumer.worker;

import com.ws.notify.redis.consumer.Consumer;
import com.ws.notify.redis.support.ConsumerHandlerMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author huang.zhangh
 * @version ConsumerWorker.java, v 0.1 2021-02-25 下午3:53
 */
public class ConsumerWorker {
    private ConsumerHandlerMethod consumerHandlerMethod;
    private Consumer consumer;

    public ConsumerWorker(ConsumerHandlerMethod consumeHandlerMethod, Consumer consumer) {
        this.consumerHandlerMethod = consumeHandlerMethod;
        this.consumer = consumer;
    }

    public void invoke() {
        Object bean = consumerHandlerMethod.getBean();
        Method method = consumerHandlerMethod.getMethod();
        String topic = consumerHandlerMethod.getTopic();
        String message;
        while ((message = consumer.getMessage(topic)) != null) {
            try {
                if (method.getReturnType().isAssignableFrom(Boolean.TYPE)) {
                    if (!((boolean) method.invoke(bean, message))) {
                        //如果消息执行失败，重试
                        consumer.retry(topic, message);
                    }
                } else {
                    method.invoke(bean, message);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}