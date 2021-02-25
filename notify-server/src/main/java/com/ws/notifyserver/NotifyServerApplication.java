package com.ws.notifyserver;

import com.ws.notify.redis.consumer.RedisMQConsumer;
import com.ws.notify.redis.consumer.annotation.OnMessage;
import com.ws.notify.redis.producer.RedisMQProducer;
import com.ws.notify.redis.producer.annotation.ToQueue;
import com.ws.notify.redis.producer.worker.ProducerWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ValueConstants;

import javax.annotation.Resource;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = "com.ws.*")
public class NotifyServerApplication {

    @Resource(name = "RedisMQProducer")
    RedisMQProducer queueProducer;

    @Resource(name = "RedisMQConsumer")
    RedisMQConsumer queueConsumer;

    @Bean
    public ProducerWorker producerWorker() {
        ProducerWorker producerWorker = new ProducerWorker();
        producerWorker.setProducer(queueProducer);
        return producerWorker;
    }

    public static void main(String[] args) {
        SpringApplication.run(NotifyServerApplication.class, args);
    }

    @RequestMapping("/")
    @ToQueue(topic = "test", async = false)
    void test(@RequestParam(name = "i") int i) {

    }

    @RequestMapping("/c")
    @OnMessage(topic = "test")
    void testc() {

    }

}
