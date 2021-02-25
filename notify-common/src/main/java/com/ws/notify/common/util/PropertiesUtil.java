/*
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.ws.notify.common.util;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author huang.zhangh
 * @version PropertiesUtil.java, v 0.1 2021-02-24 下午2:01
 */
public class PropertiesUtil {
    /**
     * @param classPath 文件路径
     * @return Properties
     * @throws IOException io异常
     */
    public static Properties parseProperties(String classPath) throws IOException {
        ClassPathResource resource = new ClassPathResource(classPath);
        Properties properties = new Properties();
        InputStream inputStream = resource.getInputStream();
        properties.load(inputStream);
        return properties;
    }
}