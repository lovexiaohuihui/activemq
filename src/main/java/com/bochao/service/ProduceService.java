package com.bochao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.UUID;

@Service
public class ProduceService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void sendMsg() {
        jmsMessagingTemplate.convertAndSend(queue, "boot消息......" + UUID.randomUUID());
    }

    /**
     * 每隔三秒钟定时发送消息
     * */
    // @Scheduled(fixedDelay = 3000)
    public void scheduledSendMsg() {
        jmsMessagingTemplate.convertAndSend(queue, "boot消息......" + UUID.randomUUID());
        System.out.println("scheduledSendMsg ok");
    }
}
