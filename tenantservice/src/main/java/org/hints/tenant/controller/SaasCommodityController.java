package org.hints.tenant.controller;

import org.hints.common.pojo.ReturnVo;
import org.hints.common.pojo.SaasCommodity;
import org.hints.common.pojo.TablePageData;
import org.hints.tenant.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Commodity")
public class SaasCommodityController {

    @Autowired
    private CommodityService CommodityService;

    @GetMapping("/list")
    public ReturnVo<TablePageData<SaasCommodity>> list(SaasCommodity saasCommodity)
    {
        TablePageData<SaasCommodity> list = CommodityService.queryPageCommodityOnUser(saasCommodity);
        return ReturnVo.success(list);
    }

    @GetMapping(value = "/{groupId}")
    public ReturnVo getInfo(@PathVariable("groupId") String groupId)
    {
        SaasCommodity saasCommodity = CommodityService.fetchCommodityById(groupId);
        return ReturnVo.success(saasCommodity);
    }
}

