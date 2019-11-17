package main.java.rocketmq.shedule;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class ScheduledMessageProducer {
    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("default");
        producer.start();
        int totalMessageToSend = 100;
        for (int i = 0; i < totalMessageToSend; ++i) {
            Message message = new Message("TestTopic", ("hello scheduled message" + i).getBytes());
            message.setDelayTimeLevel(3);
            producer.send(message);
        }
        producer.shutdown();
    }
}
