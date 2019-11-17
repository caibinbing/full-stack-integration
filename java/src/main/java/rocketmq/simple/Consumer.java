package main.java.rocketmq.simple;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;

public class Consumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("default");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.subscribe("TopicTest", "*");
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, consumeConcurrentlyContext) -> {
            System.out.printf("%s Receive New Message: %s %n", Thread.currentThread().getName(), msgs);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
        System.out.println("Consumer Started.%n");
    }
}
