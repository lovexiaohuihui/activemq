package javamq;

import org.apache.activemq.broker.BrokerService;

/**
 * 嵌入式mq实例
 * */
public class EmbedBorker {
    public static void main(String[] args) throws Exception {
        // activeMq也支持在VM中通信基于嵌入式的broker
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();
    }
}
