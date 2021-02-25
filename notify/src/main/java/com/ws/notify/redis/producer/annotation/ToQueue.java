/*
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.ws.notify.redis.producer.annotation;

import com.ws.notify.redis.support.MsgSendCallback;

import java.lang.annotation.*;

/**
 * @author huang.zhangh
 * @version ToQueue.java, v 0.1 2021-02-25 下午2:23
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ToQueue {
    String topic();

    boolean async();

}