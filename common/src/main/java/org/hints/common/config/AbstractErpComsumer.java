package org.hints.common.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2022/9/27 14:59
 */
@Component
@Slf4j
public abstract class AbstractErpComsumer implements InitializingBean {

    private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();

    public void rocketMQConsumer(DefaultMQPushConsumer consumer) throws MQClientException {

    }

    @Override
    public void afterPropertiesSet() {
        try {
            consumer.setNamesrvAddr("localhost:9876");
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.setMessageModel(MessageModel.CLUSTERING);
            consumer.setMaxReconsumeTimes(8);

            rocketMQConsumer(consumer);

            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("消息消费操作失败--" + e.getMessage());
        }
    }
}
