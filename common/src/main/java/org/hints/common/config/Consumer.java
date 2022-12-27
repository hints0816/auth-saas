package org.hints.common.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2022/9/1 16:02
 */
@Slf4j
@Component
public class Consumer extends AbstractErpComsumer {

    @Autowired
    private SaasConsumer greeBODTdpur406Listener;

    private final String TOPIC = "SAAS";
    private final String GROUP = "SAAS";
    private final String INSTANCE = "SAAS";

    @Override
    public void rocketMQConsumer(DefaultMQPushConsumer consumer) throws MQClientException {
        consumer.subscribe(TOPIC, "*");
        consumer.setConsumerGroup(GROUP);
        consumer.setInstanceName(INSTANCE);
        consumer.registerMessageListener(greeBODTdpur406Listener);
    }
}
