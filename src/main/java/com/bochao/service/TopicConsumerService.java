package com.bochao.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

/**
 * activemq 消费者
 * */
@Service
public class TopicConsumerService {

    @JmsListener(destination = "${mytopic}")
    public void receive (TextMessage textMessage) throws Exception {
        System.out.println("接收到的消息:" + textMessage.getText());
    }
}
