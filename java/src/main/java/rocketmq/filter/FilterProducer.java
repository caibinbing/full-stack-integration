package main.java.rocketmq.filter;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

public class FilterProducer {
    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {
        DefaultMQProducer producer = new DefaultMQProducer("default");
        producer.start();
        String[] tags = new String[]{"TagA", "TagB", "TagC"};
        for (int i = 0; i < 100; ++i) {
            Message msg = new Message("TopicTest", tags[i % tags.length], ("hello rocketmq " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            msg.putUserProperty("a", String.valueOf(i));
            SendResult sendResult = producer.send(msg);
        }
        producer.shutdown();
    }
}
