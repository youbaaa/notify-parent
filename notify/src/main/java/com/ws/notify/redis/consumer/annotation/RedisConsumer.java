/*
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.ws.notify.redis.consumer.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author huang.zhangh
 * @version RedisConsumer.java, v 0.1 2021-02-25 下午3:33
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RedisConsumer {
    @AliasFor(
            annotation = Component.class
    )
    String value();
}