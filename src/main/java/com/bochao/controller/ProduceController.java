package com.bochao.controller;

import com.bochao.service.ProduceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ProduceController {

    @Resource
    private ProduceService produceService;

    @GetMapping("/get")
    public String get() {
        return "hello";
    }

    @GetMapping("/send")
    public String send() {
        produceService.sendMsg();
        return "消息发送成功";
    }
}
