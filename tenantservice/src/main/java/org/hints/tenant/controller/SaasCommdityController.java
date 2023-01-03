package org.hints.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 项目名: scm-api
 * 创建时间: 2021-07-12 15:52
 * 描述: TODO
 **/
@RestController
@RequestMapping("/tenant/commdity")
public class SaasCommdityController {

    /**
     * 查询SAAS系统商品列表(租户)
     */
    @GetMapping("/list")
    public void list()
    {
    }

}

