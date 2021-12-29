package com.bochao;

import com.bochao.service.ProduceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@SpringBootTest(classes = ActiveMqMain.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestActiceMq {

    @Resource
    private ProduceService produceService;

    @Test
    public void testSend() throws Exception {
        produceService.sendMsg();
    }
}
