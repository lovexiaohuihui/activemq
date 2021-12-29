package com.bochao.javamq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * java实现activemq 消费者 主题方式
 * @author 吴军杰
 * */
public class JmsTopicConsumer {

    //mq url
    private static final String ACTIVEMQ_URL = "tcp://192.168.110.228:61616";
    // 队列名字
    private static final String TOPIC_MAME = "topic001";

    public static void main(String[] args) throws Exception {
        // 1- 创建连接工厂，使用默认的账号名密码，使用指定的url
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2- 通过连接工厂，获得连接connection 并启动访问
        Connection connection = factory.createConnection();
        connection.start();
        // 3- 创建会话session 两个参数 1 事务 2 签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4- 创建目的地（队列或者主题topic）
        Topic topic = session.createTopic(TOPIC_MAME);
        // 5- 创消息的生产者
        MessageConsumer consumer = session.createConsumer(topic);
        // 6- 监听消息消费
        // 异步非阻塞 监听器消费
        consumer.setMessageListener((message) -> {
            if (message != null && message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // 保证控制台不灭
        System.in.read();
        // 7-关闭资源
        consumer.close();
        session.close();
        connection.close();
    }
}
