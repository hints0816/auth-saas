package org.hints.tenant.service;

import org.hints.common.pojo.ReturnVo;
import org.hints.common.pojo.SaasOrder;
import org.hints.common.pojo.TablePageData;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/7 18:41
 */
public interface OrderService {

    SaasOrder saveOrder(SaasOrder SaasOrder);

    int updateOrder(SaasOrder SaasOrder);

    TablePageData<SaasOrder> selectOrderPage(SaasOrder SaasOrder);

    TablePageData<SaasOrder> selectOrderPageOnUser(SaasOrder SaasOrder);

    SaasOrder getOrderNo(String orderNO);

    SaasOrder getOrderNoOnUser(String orderNO);

    SaasOrder getOutTradeNoOrderOnUser(String outTradeNo);

    String getCheckOutTradeNo(String groupId);

    int updateDelOrderByIds(String[] orderNos);

    int updateStatusOrder(String orderNo, int orderStatus);

    int closeOrder(String[] orderNos);

    int updatePayTypeOrder(SaasOrder SaasOrder);

    int delOrderNos(String[] orderNOs);

    ReturnVo uploadPayCert(MultipartFile file, String orderNo);

    void downloadPayCert(String payCert, HttpServletResponse response);

}
