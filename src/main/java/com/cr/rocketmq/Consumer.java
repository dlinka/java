package com.cr.rocketmq;

import com.cr.common.Facility;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Consumer {

    private static String TOPIC = "ROCKETMQ_1";
    private static String CONSUMER_GROUP = "CONSUMER_4";

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(CONSUMER_GROUP);
        consumer.setNamesrvAddr("localhost:9876");
        consumer.subscribe(TOPIC, "*");
        //最大消费数量
        consumer.setConsumeMessageBatchMaxSize(1);
        //并发消费
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                msgs.forEach((msg) -> {
                    Facility.print("{},{},{}", msg.getMsgId(), msg.getQueueId(), new String(msg.getBody()));
                });
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }

}
