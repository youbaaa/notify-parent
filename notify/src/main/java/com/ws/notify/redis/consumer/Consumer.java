/*
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.ws.notify.redis.consumer;

/**
 * @author huang.zhangh
 * @version Consumer.java, v 0.1 2021-02-24 下午5:19
 */
public abstract class Consumer {

    public abstract String getMessage(String topic);

    public abstract void retry(String topic, String message);

}