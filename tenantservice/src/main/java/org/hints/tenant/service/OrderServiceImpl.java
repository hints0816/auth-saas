package org.hints.tenant.service;

import org.hints.common.pojo.ReturnVo;
import org.hints.common.pojo.SaasOrder;
import org.hints.common.pojo.TablePageData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/7 18:41
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Override
    public SaasOrder saveOrder(SaasOrder SaasOrder) {
        return null;
    }

    @Override
    public int updateOrder(SaasOrder SaasOrder) {
        return 0;
    }

    @Override
    public TablePageData<SaasOrder> selectOrderPage(SaasOrder SaasOrder) {
        return null;
    }

    @Override
    public TablePageData<SaasOrder> selectOrderPageOnUser(SaasOrder SaasOrder) {
        return null;
    }

    @Override
    public SaasOrder getOrderNo(String orderNO) {
        return null;
    }

    @Override
    public SaasOrder getOrderNoOnUser(String orderNO) {
        return null;
    }

    @Override
    public SaasOrder getOutTradeNoOrder(String outTradeNo) {
        return null;
    }

    @Override
    public SaasOrder getOutTradeNoOrderOnUser(String outTradeNo) {
        return null;
    }

    @Override
    public String getCheckOutTradeNo(String groupId) {
        return null;
    }

    @Override
    public int updateDelOrderById(String orderNo) {
        return 0;
    }

    @Override
    public int updateDelOrderByIds(String[] orderNos) {
        return 0;
    }

    @Override
    public int updateStatusOrder(String orderNo, int orderStatus) {
        return 0;
    }

    @Override
    public int closeOrder(String[] orderNos) {
        return 0;
    }

    @Override
    public int updatePayTypeOrder(SaasOrder SaasOrder) {
        return 0;
    }

    @Override
    public int delOrderNos(String[] orderNOs) {
        return 0;
    }

    @Override
    public ReturnVo uploadPayCert(MultipartFile file, String orderNo) {
        return null;
    }

    @Override
    public void downloadPayCert(String payCert, HttpServletResponse response) {

    }
}
