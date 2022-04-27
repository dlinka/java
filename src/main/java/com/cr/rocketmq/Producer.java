package com.cr.rocketmq;

import com.cr.common.Facility;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Producer {

    private static String TOPIC_NAME = "ROCKETMQ_1";
    private static String PRODUCER_GROUP = "PRODUCER_GROUP_ROCKETMQ";

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        syncSend();
        //asyncSend();
        //sendNoResult();
    }

    static void syncSend() throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer(PRODUCER_GROUP);
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        for (int i = 1; i <= 100; i++) {
            String message = "Hello World!";
            Message msg = new Message(TOPIC_NAME, "",  message.getBytes());
            SendResult sendResult = producer.send(msg);
            Facility.print(sendResult);
        }
        producer.shutdown();
    }

    static void asyncSend() throws MQClientException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer(PRODUCER_GROUP);
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);

        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 100; i++) {
            String msg = "ORDER - " + i;
            Message message = new Message(TOPIC_NAME, "", msg.getBytes());
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println(sendResult);
                    latch.countDown();
                }

                @Override
                public void onException(Throwable e) {
                    e.printStackTrace();
                    latch.countDown();
                }
            });
        }
        latch.await();
        producer.shutdown();
    }

    static void sendNoResult() throws MQClientException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer(PRODUCER_GROUP);
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        for (int i = 0; i < 20000; i++) {
            String msg = "send no result - " + i;
            Message message = new Message(TOPIC_NAME, "", msg.getBytes());
            producer.sendOneway(message);
        }
        producer.shutdown();
    }

}
