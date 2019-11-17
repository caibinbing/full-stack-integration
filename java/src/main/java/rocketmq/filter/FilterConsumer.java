package main.java.rocketmq.filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;

public class FilterConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("default");
        consumer.subscribe("TopicTest", MessageSelector.bySql("a between 0 and 3"));
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> ConsumeConcurrentlyStatus.CONSUME_SUCCESS);
        consumer.start();
    }
}
