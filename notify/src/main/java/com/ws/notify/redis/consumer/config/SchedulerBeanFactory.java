/*
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.ws.notify.redis.consumer.config;

import com.ws.notify.redis.consumer.Consumer;
import com.ws.notify.redis.consumer.annotation.OnMessage;
import com.ws.notify.redis.consumer.annotation.RedisConsumer;
import com.ws.notify.redis.consumer.worker.ConsumerWorker;
import com.ws.notify.redis.support.ConsumerHandlerMethod;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;


/**
 * @author huang.zhangh
 * @version SchedulerBeanFactory.java, v 0.1 2021-02-25 下午3:36
 */
public class SchedulerBeanFactory implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    private Consumer consumer;

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private void init() {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(RedisConsumer.class);
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            String name = entry.getKey();
            Object bean = entry.getValue();
            Class<?> clazz = applicationContext.getType(name);
            assert clazz != null;
            for (Method method : clazz.getMethods()) {
                if (method.isAnnotationPresent(OnMessage.class)) {
                    OnMessage onMessage = method.getAnnotation(OnMessage.class);
                    String topic = onMessage.topic();
                    ConsumerHandlerMethod consumeHandlerMethod = new ConsumerHandlerMethod(method, bean, topic);
                    ConsumerWorker consumerWorker = new ConsumerWorker(consumeHandlerMethod, consumer);

                    //注册JobDetail
                    String jobDetailBeanName = buildJobDetailBeanName(consumeHandlerMethod);
                    registerJobDetail(beanFactory, jobDetailBeanName, consumerWorker);

                    //注册Trigger
                    registerTrigger(beanFactory, buildTriggerBeanName(consumeHandlerMethod), jobDetailBeanName);
                }
            }
        }
        //注册Scheduler
        registerScheduler(beanFactory);
    }

    private String buildJobDetailBeanName(ConsumerHandlerMethod method) {
        return method.getTopic().concat("JobDetail");
    }

    private String buildTriggerBeanName(ConsumerHandlerMethod method) {
        return method.getTopic().concat("Trigger");
    }

    private void registerJobDetail(DefaultListableBeanFactory beanFactory, String beanName, ConsumerWorker worker) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(MethodInvokingJobDetailFactoryBean.class);
        beanDefinitionBuilder.addPropertyValue("targetObject", worker);
        beanDefinitionBuilder.addPropertyValue("targetMethod", "invoke");
        beanDefinitionBuilder.addPropertyValue("concurrent", true);
        beanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
    }

    private void registerTrigger(DefaultListableBeanFactory beanFactory, String beanName, String jobDetailBeanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(SimpleTriggerFactoryBean.class);
        beanDefinitionBuilder.addPropertyReference("jobDetail", jobDetailBeanName);
        beanDefinitionBuilder.addPropertyValue("startDelay", 1000); //可自定义但是没什么必要(?)
        beanDefinitionBuilder.addPropertyValue("repeatInterval", 2000); //可自定义
        beanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
    }

    private void registerScheduler(DefaultListableBeanFactory beanFactory) {
        Collection<Trigger> triggers = beanFactory.getBeansOfType(Trigger.class).values();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(SchedulerFactoryBean.class);
        beanDefinitionBuilder.addPropertyValue("triggers", triggers);
        beanFactory.registerBeanDefinition("consumerScheduler", beanDefinitionBuilder.getBeanDefinition());
    }
}