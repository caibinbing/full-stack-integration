package main.java.rocketmq.broadcasting;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

public class BroadcastConsumer {
    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("default");
        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.subscribe("Topic", "TagA || TagC || TagD");
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
        System.out.printf("Broadcast Consumer Started.%n");
    }

}
