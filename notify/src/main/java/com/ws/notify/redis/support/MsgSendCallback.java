/*
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.ws.notify.redis.support;

/**
 * @author huang.zhangh
 * @version MsgSendCallback.java, v 0.1 2021-02-24 下午5:35
 */
public interface MsgSendCallback {
    /**
     * 发送成功回调的方法.
     *
     * @param sendResult 发送结果
     */
    void onSuccess(final MsgSendResult sendResult);

    /**
     * 发送失败回调方法.
     *
     * @param context 失败上下文.
     */
    void onException(final Throwable context);
}