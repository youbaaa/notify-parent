/*
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.ws.notify.redis.producer;

import com.ws.notify.redis.support.MsgSendCallback;
import com.ws.notify.redis.support.MsgSendResult;

/**
 * @author huang.zhangh
 * @version Producer.java, v 0.1 2021-02-24 下午5:19
 */

public abstract class Producer {

    /**
     * 发送消息
     *
     * @param topic        主题
     * @param msg          消息
     * @param async        异步
     * @param sendCallback 回调
     * @return MsgSendResult
     */
    public abstract MsgSendResult send(String topic, String msg,
                                       boolean async, MsgSendCallback sendCallback);

}