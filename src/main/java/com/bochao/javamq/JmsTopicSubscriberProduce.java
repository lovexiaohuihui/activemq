package com.bochao.javamq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * java实现mq生产者 主题方式 持久化方式
 * @author 吴军杰
 * */
public class JmsTopicSubscriberProduce {

    //mq url
    private static final String ACTIVEMQ_URL = "tcp://192.168.110.228:61616";
    // 队列名字
    private static final String TOPIC_MAME = "topic001";

    public static void main(String[] args) throws Exception {

        // 1- 创建连接工厂，使用默认的账号名密码，使用指定的url
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2- 通过连接工厂，获得连接connection 并启动访问
        Connection connection = factory.createConnection();
        // 3- 创建会话session 两个参数 1 事务 2 签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4- 创建目的地（队列或者主题topic）
        Topic topic = session.createTopic(TOPIC_MAME);
        // 5- 创建消息的生产者
        MessageProducer producer = session.createProducer(topic);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        connection.start();
        // 6- 生产者发送消息到队列中
        for (int i = 1; i <= 3; i++) {
            // 7- 创建消息
            TextMessage message = session.createTextMessage("msg*--------------" + i);
            // 8- 发送消息
            producer.send(message);
        }
        // 9- 关闭资源
        producer.close();
        session.close();
        connection.close();

        System.out.println("消息发送成功");
    }
}
