package com.bochao.javamq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * java实现activemq 消费者 队列方式
 * @author 吴军杰
 * */
public class JmsQueueConsumer {

    //mq url
    // private static final String ACTIVEMQ_URL = "tcp://192.168.110.228:61616";
    // private static final String ACTIVEMQ_URL = "tcp://localhost:61616";
    private static final String ACTIVEMQ_URL = "nio://192.168.110.228:61618";
    // 队列名字
    private static final String QUEUE_MAME = "queue001";

    public static void main(String[] args) throws Exception {
        // 1- 创建连接工厂，使用默认的账号名密码，使用指定的url
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2- 通过连接工厂，获得连接connection 并启动访问
        Connection connection = factory.createConnection();
        connection.start();
        // 3- 创建会话session 两个参数 1 事务 2 签收
        // 自动签收 无事务
        // Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 手动签收 无事务
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        // 自动签收 有事务
        // Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        // 4- 创建目的地（队列或者主题topic）
        Queue queue = session.createQueue(QUEUE_MAME);
        // 5- 创消息的生产者
        MessageConsumer consumer = session.createConsumer(queue);
        // 6- 监听消息消费
        // 阻塞消费
        while (true) {
            TextMessage textMessage = (TextMessage) consumer.receive(5000);
            if (textMessage == null) {
                break;
            }
            System.out.println("消费了消息-----" + textMessage.getText());
            // 手动确定签收 事务开启无论如何方式都自动签收 不需要手动确定签收
            textMessage.acknowledge();
        }
        // 异步非阻塞 监听器消费
//        consumer.setMessageListener(new MessageListener() {
//            public void onMessage(Message message) {
//                if (message != null && message instanceof TextMessage) {
//                    TextMessage textMessage = (TextMessage) message;
//                    try {
//                        System.out.println(textMessage.getText());
//                        System.out.println("消息属性" + textMessage.getStringProperty("007"));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                // 接收 MapMessage
//                if (message != null && message instanceof MapMessage) {
//                    MapMessage mapMessage = (MapMessage) message;
//                    try {
//                        System.out.println(mapMessage.getString("111"));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
        // 保证控制台不灭
        // System.in.read();
        // 7-关闭资源
        consumer.close();
        // 事务提交
        // session.commit();
        session.close();
        connection.close();
    }
}
