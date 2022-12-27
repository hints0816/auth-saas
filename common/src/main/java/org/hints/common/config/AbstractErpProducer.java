package org.hints.common.config;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2022/12/27 16:29
 */
@Component
public class AbstractErpProducer implements InitializingBean {

    private DefaultMQProducer producer;

    @Override
    public void afterPropertiesSet() {
        producer = new DefaultMQProducer("SAAS");
        producer.setNamesrvAddr("localhost:9876");
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public DefaultMQProducer getProducer() {
        return producer;
    }
}
