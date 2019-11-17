package main.java.rocketmq.batch;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.LinkedList;
import java.util.List;

public class BatchProducer {
    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("default");
        producer.start();
        String topic = "BatchTest";
        List<Message> messages = new LinkedList<>();
        messages.add(new Message(topic, "TopA", "OrderId001", "hello rocketmq 0".getBytes()));
        messages.add(new Message(topic, "TopA", "OrderId002", "hello rocketmq 1".getBytes()));
        messages.add(new Message(topic, "TopA", "OrderId003", "hello rocketmq 2".getBytes()));
        producer.send(messages);
        producer.shutdown();
    }
}
