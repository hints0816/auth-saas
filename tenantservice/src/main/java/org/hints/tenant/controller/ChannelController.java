package org.hints.tenant.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.hints.common.config.AbstractErpProducer;
import org.hints.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @Description TODO
 * @Author hints
 * @Date 2022/9/9 16:14
 */

@RestController
@RequestMapping
public class ChannelController {

    @Autowired
    private TenantService tenantService;

    @Autowired
    private AbstractErpProducer abstractErpProducer;

    @PostMapping("/create")
    public void create(Principal principal) throws Exception{

        tenantService.create("", "",
                LocalDateTime.now(), 10L,"");

        HashMap<String,Object> map = new HashMap<>();
        map.put("clientid","SAAS123123");
        map.put("password","123456");
        String jsonString = JSONObject.toJSONString(map);
        Message msg = new Message(
                "SAAS",
                null,
                (jsonString).getBytes(RemotingHelper.DEFAULT_CHARSET)
        );
        SendResult sendResult = abstractErpProducer.getProducer().send(msg);
        System.out.printf("%s%n", sendResult);
    }

}
