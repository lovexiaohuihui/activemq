package com.bochao.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

// @Configuration
@Component
@EnableJms // 开启mq
public class ActiveMqConfig {

    @Value("${myqueuename}")
    private String myQueueName;

    @Bean
    public Queue getQueue() {
        return new ActiveMQQueue(myQueueName);
    }
}
