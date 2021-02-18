package main.java.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class SimpleProducer {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("enter topic name");
            return;
        }
        String topicName = args[0];
        Properties props = new Properties();
        //localhost id
        props.put("bootstrap.servers", "localhost:9092");
        //acknowledgements for procuder requests
        props.put("acks", "all");
        //if the request fails , the producer can automatically retry
        props.put("retries", 0);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 10; ++i) {
            producer.send(new ProducerRecord<>(topicName, Integer.toString(i), Integer.toString(i)));
            System.out.println("Message sent successfully");
            producer.close();
        }
    }
}
