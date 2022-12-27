package org.hints.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 180686 on 2021/6/23 14:46
 */
@Component
public class SaasConsumer implements MessageListenerConcurrently {

    @Autowired
    private MultiRouteDataSource dynamicDataSource;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        try {
            if (list != null && list.size() > 0) {
                MessageExt messageExt = list.get(0);
                HashMap<String, Object> map = new HashMap<String, Object>();
                String clientid = map.get("clientid") == null ? "" : map.get("clientid").toString();
                String password = map.get("password") == null ? "" : map.get("password").toString();
                DruidDataSource druidDataSource = new DruidDataSource();
                druidDataSource.setUrl("jdbc:oracle:thin:@10.2.25.123/ggrdattest");
                druidDataSource.setUsername(clientid);
                druidDataSource.setPassword(password);
                dynamicDataSource.addDataSource(druidDataSource);
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        } catch (Exception e) {
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
    }
}
