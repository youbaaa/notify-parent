/*
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.ws.notify.redis.support;

import java.lang.reflect.Method;

/**
 * @author huang.zhangh
 * @version ConsumeHandlerMethod.java, v 0.1 2021-02-25 下午3:46
 */
public class ConsumerHandlerMethod {
    private Method method;
    private Object bean;
    private String topic;

    public ConsumerHandlerMethod(Method method, Object bean, String topic) {
        this.method = method;
        this.bean = bean;
        this.topic = topic;
    }

    public Method getMethod() {
        return method;
    }

    public Object getBean() {
        return bean;
    }

    public String getTopic() {
        return topic;
    }
}