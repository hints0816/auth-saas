package org.hints.tenant.controller.admin;

import org.hints.common.pojo.ReturnVo;
import org.hints.common.pojo.SaasOrder;
import org.hints.common.pojo.TablePageData;
import org.hints.tenant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public ReturnVo<TablePageData<SaasOrder>> list(SaasOrder saasOrder) {
        TablePageData<SaasOrder> saasOrderTablePageData = orderService.selectOrderPage(saasOrder);
        return ReturnVo.success(saasOrderTablePageData);
    }

    @GetMapping(value = "/{orderNo}")
    public ReturnVo getInfo(@PathVariable("orderNo") String orderNo)
    {
        return ReturnVo.success(orderService.getOrderNo(orderNo));
    }

    @PutMapping(value = "/status")
    public ReturnVo updateStatusOrder(@RequestParam String orderNo, @RequestParam int orderStatus){
        return ReturnVo.toAjax(orderService.updateStatusOrder(orderNo,orderStatus));
    }

}
