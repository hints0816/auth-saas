package org.hints.tenant.controller.admin;

import org.hints.common.pojo.ReturnVo;
import org.hints.common.pojo.SaasCommodity;
import org.hints.common.pojo.TablePageData;
import org.hints.tenant.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/admin/commodity")
public class AdminCommodityController {

    @Autowired
    private CommodityService commodityService;

    @GetMapping("/list")
    public ReturnVo<TablePageData<SaasCommodity>> list(SaasCommodity saasCommodity) {
        TablePageData<SaasCommodity> list = commodityService.queryPageCommodity(saasCommodity);
        return ReturnVo.success(list);
    }

    @GetMapping(value = "/{groupId}")
    public ReturnVo getInfo(@PathVariable("groupId") String groupId) {
        SaasCommodity saasCommodity = commodityService.fetchCommodityById(groupId);
        return ReturnVo.success(saasCommodity);
    }

    @PostMapping
    public ReturnVo add(@RequestBody SaasCommodity SaasCommodity) {
        return ReturnVo.toAjax(commodityService.addCommodity(SaasCommodity));
    }

    @PutMapping
    public ReturnVo edit(@RequestBody SaasCommodity SaasCommodity) {
        return ReturnVo.toAjax(commodityService.updateCommodity(SaasCommodity));
    }

    @DeleteMapping("/{groupIds}")
    public ReturnVo remove(@PathVariable String[] groupIds) {
        return ReturnVo.toAjax(commodityService.deleteCommodity(groupIds));
    }

}

