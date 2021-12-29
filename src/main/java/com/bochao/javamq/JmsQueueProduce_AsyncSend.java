package com.bochao.javamq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;

import javax.jms.*;
import java.util.UUID;

/**
 * java实现mq生产者 队列方式(异步投递确认消息是否发送成功)
 * @author 吴军杰
 * */
public class JmsQueueProduce_AsyncSend {

    //mq url
    // private static final String ACTIVEMQ_URL = "tcp://192.168.110.228:61616";
    // private static final String ACTIVEMQ_URL = "tcp://localhost:61616";
    private static final String ACTIVEMQ_URL = "nio://192.168.110.228:61618";
    // 队列名字
    private static final String QUEUE_MAME = "queue001";

    public static void main(String[] args) throws Exception {

        // 1- 创建连接工厂，使用默认的账号名密码，使用指定的url
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 开启异步投递
        factory.setUseAsyncSend(true);
        // 2- 通过连接工厂，获得连接connection 并启动访问
        Connection connection = factory.createConnection();
        connection.start();
        // 3- 创建会话session 两个参数 1 事务 2 签收
        // Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        // 4- 创建目的地（队列或者主题topic）
        Queue queue = session.createQueue(QUEUE_MAME);
        // 5- 创建消息的生产者
        ActiveMQMessageProducer activeMQMessageProducer = (ActiveMQMessageProducer) session.createProducer(queue);
        // 消息持久化
        activeMQMessageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        // 6- 生产者发送消息到队列中
        for (int i = 1; i <= 3; i++) {
            // 7- 创建消息
            TextMessage message = session.createTextMessage("msg*--------------" + i);
            message.setStringProperty("007", "vip");
            String uuid = UUID.randomUUID().toString();
            message.setJMSMessageID(uuid);
            // 8- 发送消息
            activeMQMessageProducer.send(message, new AsyncCallback() {
                @Override
                public void onSuccess() {
                    System.out.println(uuid + "send ok");
                }

                @Override
                public void onException(JMSException e) {
                    System.out.println(uuid + "send fail");
                }
            });

            // mapMessage 消息体
//            MapMessage mapMessage = session.createMapMessage();
//            mapMessage.setString("111", "哈哈哈哈");
//            producer.send(mapMessage);
        }
        // 9- 关闭资源
        activeMQMessageProducer.close();
        session.commit();
        session.close();
        connection.close();

        System.out.println("消息发送成功");
    }
}
