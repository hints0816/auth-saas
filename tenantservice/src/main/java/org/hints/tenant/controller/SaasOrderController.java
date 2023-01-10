package org.hints.tenant.controller;

import org.hints.common.pojo.ReturnVo;
import org.hints.common.pojo.SaasOrder;
import org.hints.common.pojo.TablePageData;
import org.hints.tenant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/order")
public class SaasOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/list")
    public ReturnVo<TablePageData<SaasOrder>> listOnUser(SaasOrder SaasOrder) {
        TablePageData<SaasOrder> SaasOrderTablePageData = orderService.selectOrderPageOnUser(SaasOrder);
        return ReturnVo.success(SaasOrderTablePageData);
    }

    @GetMapping(value = "/{orderNo}")
    public ReturnVo getInfo(@PathVariable("orderNo") String orderNo)
    {
        return ReturnVo.success(orderService.getOrderNoOnUser(orderNo));
    }

    @GetMapping(value = "/out_trade_no/{outTradeNo}")
    public ReturnVo getInfoByTradeNoOrder(@PathVariable("outTradeNo") String outTradeNo)
    {
        return ReturnVo.success(orderService.getOutTradeNoOrderOnUser(outTradeNo));
    }

    @GetMapping("/check")
    public ReturnVo getCheckOutTradeNo(@RequestParam("group_id") String groupId) {
        return ReturnVo.success(orderService.getCheckOutTradeNo(groupId));
    }

    @PostMapping(value = "/submit")
    public ReturnVo insert(@RequestBody SaasOrder SaasOrder){
        SaasOrder result = orderService.saveOrder(SaasOrder);
        return ReturnVo.success(result);
    }

    @PutMapping
    public ReturnVo edit(@RequestBody SaasOrder SaasOrder)
    {
        return ReturnVo.toAjax(orderService.updateOrder(SaasOrder));
    }

    @DeleteMapping("/close/{orderNos}")
    public ReturnVo close(@PathVariable String[] orderNos)
    {
        return ReturnVo.toAjax(orderService.closeOrder(orderNos));
    }

    /**
     * 软删除(租户)
     */
    @DeleteMapping("/del/{orderNos}")
    public ReturnVo removeUpdate(@PathVariable String[] orderNos)
    {
        return ReturnVo.toAjax(orderService.updateDelOrderByIds(orderNos));
    }

    /**
     * 线下支付（上传文件，说明）
     */
    @PutMapping(value = "/paytype")
    public ReturnVo updatePayTypeOrder(@RequestBody SaasOrder SaasOrder){
        return ReturnVo.toAjax(orderService.updatePayTypeOrder(SaasOrder));
    }

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public ReturnVo upload(MultipartFile file, String orderNo) {
        return orderService.uploadPayCert(file, orderNo);
    }

    /**
     * 下载文件
     */
    @GetMapping("/download")
    public void download(@RequestParam String path, HttpServletResponse response) {
        orderService.downloadPayCert(path, response);
    }

}
