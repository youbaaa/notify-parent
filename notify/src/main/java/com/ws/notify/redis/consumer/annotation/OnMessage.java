/*
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.ws.notify.redis.consumer.annotation;

import java.lang.annotation.*;

/**
 * @author huang.zhangh
 * @version OnMessage.java, v 0.1 2021-02-25 下午3:49
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OnMessage {
    String topic();
}