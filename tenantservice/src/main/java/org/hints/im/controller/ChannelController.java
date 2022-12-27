package org.hints.im.controller;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.hints.common.config.AbstractErpProducer;
import org.hints.common.config.MultiRouteDataSource;
import org.hints.im.dao.SaasOracleDao;
import org.hints.im.dao.SaasTenantDao;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Description TODO
 * @Author hints
 * @Date 2022/9/9 16:14
 */

@RestController
@RequestMapping
public class ChannelController {

    @Autowired
    private Dao dao;

    @Autowired
    private MultiRouteDataSource dataSource;

    @Autowired
    private SaasTenantDao saasTenantDao;

    @Autowired
    private SaasOracleDao saasOracleDao;

    @Autowired
    private AbstractErpProducer abstractErpProducer;

    @PostMapping("/create")
    public void create(Principal principal) throws Exception{
        Message msg = new Message(
                "SAAS",
                null,
                ("").getBytes(RemotingHelper.DEFAULT_CHARSET)
        );
        SendResult sendResult = abstractErpProducer.getProducer().send(msg);
        System.out.printf("%s%n", sendResult);
    }

}
