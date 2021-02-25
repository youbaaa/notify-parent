/*
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.ws.notify.common.util;

import java.io.*;

/**
 * @author huang.zhangh
 * @version SerializeUtil.java, v 0.1 2021-02-25 下午2:02
 */
public class SerializeUtil {
    /**
     * 序列化
     *
     * @param object 对象
     */
    public static byte[] serialize(Object object) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos);) {
            oos.writeObject(object);
            return baos.toByteArray();
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * 反序列化
     *
     * @param bytes 二进制
     */
    public static Object unserialize(byte[] bytes) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais);) {
            return ois.readObject();
        } catch (Exception ignored) {
        }
        return null;
    }
}