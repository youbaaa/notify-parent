/*
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.ws.notify.redis.support;

/**
 * @author huang.zhangh
 * @version MsgSendResult.java, v 0.1 2021-02-24 下午5:31
 */
public class MsgSendResult {
    /**
     * 消息ID
     */
    private String messageId;
    /**
     * 主题
     */
    private String topic;
    private YesNo yesNo;
    /**
     * 异常
     */
    private Throwable exception;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public YesNo getYesNo() {
        return yesNo;
    }

    public void setYesNo(YesNo yesNo) {
        this.yesNo = yesNo;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "MsgSendResult{" +
                "messageId='" + messageId + '\'' +
                ", topic='" + topic + '\'' +
                ", yesNo=" + yesNo +
                ", exception=" + exception +
                '}';
    }
}