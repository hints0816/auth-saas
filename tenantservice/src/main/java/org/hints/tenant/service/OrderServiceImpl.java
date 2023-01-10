package org.hints.tenant.service;

import org.hints.common.pojo.ReturnVo;
import org.hints.common.pojo.SaasCommodity;
import org.hints.common.pojo.SaasOrder;
import org.hints.common.pojo.TablePageData;
import org.hints.common.utils.UUIDUtil;
import org.hints.tenant.dao.SaasCommodityDao;
import org.hints.tenant.dao.SaasOrderDao;
import org.hints.tenant.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/7 18:41
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private SaasOrderDao saasOrderDao;

    @Autowired
    private SaasCommodityDao saasCommodityDao;

    @Autowired
    private TenantService tenantService;

    private static final String MONTH = "月";
    private static final String WEEK = "周";
    private static final String YEAR = "年";

    @Override
    public SaasOrder saveOrder(SaasOrder saasOrder) {
        /** 时间戳生成订单号 */
        long timeNew =  System.currentTimeMillis();
        int randomNum = (int)((Math.random()*9+1)*100000);
        String orderNo = "" + timeNew + randomNum;

        saasOrder.setOrder_no(orderNo);
        saasOrder.setTenantId(String.valueOf(SecurityUtil.getJwtInfo().getUser_id()));
        /** UUID生成流水号 */
        saasOrder.setOutTradeNo(UUIDUtil.getUUID().trim());
        /** 默认为不删除 */
        saasOrder.setIsDelete(1);
        /** 审批状态为默认 */
        saasOrder.setOrderStatus(1);
        /** 支付状态为未支付 */
        saasOrder.setPayStatus(1);
        /** 订单类型为新购 */
        saasOrder.setOrderType(1);

        /** 获取商品单价计算订单总金额 */
        SaasCommodity groupIdCommodity = saasCommodityDao.getGroupIdCommodity(saasOrder.getGroupId());
        if (groupIdCommodity!= null) {
            double fee = groupIdCommodity.getFee();
            saasOrder.setOrderMoney(BigDecimal.valueOf(fee*saasOrder.getGroupPeriodNum()));
        }
        saasOrder.setGroupPeriodUnit(groupIdCommodity.getUnit());
        saasOrder.setCreate_time(LocalDateTime.now());
        SaasOrder insert = saasOrderDao.insertSaasOrder(saasOrder);
        return insert;
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
    public TablePageData<SaasOrder> selectOrderPageOnUser(SaasOrder saasOrder) {
        String tenantId = SecurityUtil.getJwtInfo().getUser_id().toString();
        saasOrder.setIsDelete(1);
        saasOrder.setTenantId(tenantId);
        TablePageData<SaasOrder> saasSysOrderTablePageData = saasOrderDao.selectOrderPage(saasOrder);
        return saasSysOrderTablePageData;
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
        Long tenantid = SecurityUtil.getJwtInfo().getUser_id();
        List<SaasOrder> checkOutTradeNo = saasOrderDao.getCheckOutTradeNo(String.valueOf(tenantid), 1, 1, groupId);
        return checkOutTradeNo.size()>0?checkOutTradeNo.get(0).getOrder_no():null;
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
        int i = saasOrderDao.updateStatusOrder(orderNo,orderStatus);
        if (i > 0){
            /** 状态->通过 */
            if(orderStatus == 2) {
                /** 获取订单的商品 */
                SaasOrder orderNoIdOrder = saasOrderDao.getOrderNoIdOrder(orderNo);
                String tenantId = orderNoIdOrder.getTenantId();
                String groupId = orderNoIdOrder.getGroupId();
                Long groupPeriodNum = orderNoIdOrder.getGroupPeriodNum();
                SaasCommodity saasCommodity = saasCommodityDao.getGroupIdCommodity(groupId);

                /** 计算套餐时间期限 */
                LocalDateTime plus = LocalDateTime.now();
                if (WEEK.equals(orderNoIdOrder.getGroupPeriodUnit())) {
                    plus = LocalDateTime.now().plus(groupPeriodNum, ChronoUnit.WEEKS);
                } else if (MONTH.equals(orderNoIdOrder.getGroupPeriodUnit())) {
                    plus = LocalDateTime.now().plus(groupPeriodNum, ChronoUnit.MONTHS);
                } else if (YEAR.equals(orderNoIdOrder.getGroupPeriodUnit())) {
                    plus = LocalDateTime.now().plus(groupPeriodNum, ChronoUnit.YEARS);
                }
                final LocalDateTime expireTime = plus;

                String id = tenantService.toBeTenant(tenantId,groupId,plus,10L,"");

                /** 套餐插件 */
//                if (id!=null) {
//                    List<String> addons = Arrays.asList(saasCommodity.getAddonArray().split(","));
//                    if(addons.size() != 0){
//                        addons.forEach(addonId -> {
//                            SaasTenantAddon saasTenantAddon = new SaasTenantAddon();
//                            saasTenantAddon.setTenantId(id);
//                            saasTenantAddon.setAddonId(Long.valueOf(addonId));
//                            saasTenantAddon.setExpireTime(expireTime);
//                            saasTenantAddon.setStatus(0L);
//                            saasTenantAddonDao.insertSaasTenantAddon(saasTenantAddon);
//                        });
//                    }
//                }
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int closeOrder(String[] orderNos) {
        //TODO 关闭订单检查是否本人订单（后续优化）
        String tenantId = SecurityUtil.getJwtInfo().getUser_id().toString();
        for (String orderNo : orderNos) {
            SaasOrder orderNoIdOrder = saasOrderDao.getOrderNoIdOrder(orderNo);
            if (!tenantId.equals(orderNoIdOrder.getTenantId())) {
                return 0;
            }
        }
        int i = 0;
        for (String orderNo : orderNos) {
            i+= saasOrderDao.updateStatusOrder(orderNo, 2);
        }
        return i;
    }

    @Override
    public int updatePayTypeOrder(SaasOrder saasOrder) {
        /** 订单号获取订单信息 */
        SaasOrder orderNoIdOrder = saasOrderDao.getOrderNoIdOrder(saasOrder.getOrder_no());
        orderNoIdOrder.setPayCert(saasOrder.getPayCert());
        String tenantId = SecurityUtil.getJwtInfo().getUser_id().toString();
        /** 是否为本人操作 */
        if (!tenantId.equals(orderNoIdOrder.getTenantId())) {
            return 0;
        }
        orderNoIdOrder.setPayCertex(saasOrder.getPayCertex());
        orderNoIdOrder.setPayTime(LocalDateTime.now());
        /** 支付方式为2线下付款 */
        orderNoIdOrder.setPayType(2);
        /** 支付状态为2已支付 */
        orderNoIdOrder.setPayStatus(2);
        return saasOrderDao.updatePayTypeOrder(orderNoIdOrder);
    }

    @Override
    public int delOrderNos(String[] orderNOs) {
        return saasOrderDao.delOrderNos(orderNOs);
    }

    @Override
    public ReturnVo uploadPayCert(MultipartFile file, String orderNo) {
        return null;
    }

    @Override
    public void downloadPayCert(String payCert, HttpServletResponse response) {

    }

}
