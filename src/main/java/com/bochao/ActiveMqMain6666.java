package com.bochao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJms // 开启mq
@EnableScheduling
public class ActiveMqMain6666 {
    public static void main(String[] args) {
        SpringApplication.run(ActiveMqMain6666.class, args);
    }
}
