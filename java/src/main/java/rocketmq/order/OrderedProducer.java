package main.java.rocketmq.order;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

public class OrderedProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException, UnsupportedEncodingException {
        MQProducer producer = new DefaultMQProducer("default");
        producer.start();
        String[] tags = new String[]{"TagA", "TagB", "TagC"};
        for (int i = 0; i < 100; ++i) {
            int orderId = i % 10;
            Message msg = new Message("TopicTest", tags[i % tags.length], "KEY" + i, ("hello rocketmq " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg, (mqs, message, arg) -> {
                Integer id = (Integer)arg;
                int index = id % mqs.size();
                return mqs.get(index);
            }, orderId);
            System.out.printf("%s%n", sendResult);
        }
    }
}
