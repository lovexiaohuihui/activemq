package com.bochao.service;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Topic;
import java.util.UUID;

@Service
public class TopicProduceService {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private Topic topic;

    @Scheduled(fixedDelay = 3000)
    public void scheduledSendMsg() {
        jmsMessagingTemplate.convertAndSend(topic, "topic消息......" + UUID.randomUUID());
        System.out.println("topic scheduledSendMsg ok");
    }
}
