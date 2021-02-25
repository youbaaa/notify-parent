/*
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.ws.notify.redis.producer.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author huang.zhangh
 * @version Producer.java, v 0.1 2021-02-25 下午2:23
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RedisProducer {
    @AliasFor(
            annotation = Component.class
    )
    String value();
}