package org.hints.tenant.dao;

import org.apache.commons.lang3.StringUtils;
import org.hints.common.pojo.SaasCommodity;
import org.hints.common.pojo.SaasOrder;
import org.hints.common.pojo.TablePageData;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.pager.Pager;
import org.nutz.trans.Atom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/10 15:05
 */
@Repository
public class SaasOrderDao {

    @Autowired
    private Dao dao;

    /**
     * 新增
     * @param saasOrder
     * @return
     */
    public SaasOrder insertSaasOrder(SaasOrder saasOrder){
        SaasOrder insert = dao.insert(saasOrder);
        return insert;
    }

    /**
     * 更新
     * @param saasOrder
     * @return
     */
    public int updateSaasOrder(SaasOrder saasOrder){
        final int[] a = {0};
        FieldFilter.locked(SaasCommodity.class,"^create_time$").run(new Atom() {
            @Override
            public void run() {
                a[0] = dao.update(saasOrder);
            }
        });
        return a[0];
    }


    /**
     * 关闭订单 1 删除
     * @param orderNo
     * @returnD
     */
    public int updateDelOrderById(String orderNo){
        int updateCount = dao.update(SaasOrder.class, Chain.make("is_delete",2),
                Cnd.where("order_no","=",orderNo));
        return updateCount;
    }


    /**
     * 关闭订单 1 删除
     * @returnD
     */
    public int updateDelOrderByIds(String[] orderNos){
        int updateCount = dao.update(SaasOrder.class,Chain.make("is_delete",2),
                Cnd.where("order_no","in",orderNos));
        return updateCount;
    }

    /**
     * 1.4、更新订单（审批通过）
     * @param orderNo
     * @returnD
     */
    public int updateStatusOrder(String orderNo,int orderStatus){
        int updateCount = dao.update(SaasOrder.class,Chain.make("ORDER_STATUS",orderStatus),
                Cnd.where("order_no","=",orderNo));
        return updateCount;
    }

    /**
     * 线下支付
     * @param saasOrder
     * @return
     */
    public int updatePayTypeOrder(SaasOrder saasOrder){
        final int[] a = {0};
        FieldFilter.create(SaasOrder.class,"^order_no|payCert|payCertex|orderStatus|payStatus|payTime|payType$", true).run(new Atom(){
            @Override
            public void run(){
                a[0] = dao.update(saasOrder);
            }
        });
        return a[0];
    }

    /**
     * 更新图片
     */
    public int updateOrderPayCert(String orderNo, String payCert){
        return dao.update(SaasOrder.class, Chain.make("pay_cert", payCert), Cnd.where("order_no","=",orderNo));
    }

    /**
     * id查询
     * @param orderNo
     * @return
     */
    public SaasOrder getOrderNoIdOrder(String orderNo){
        SaasOrder saasOrder = dao.fetch(SaasOrder.class, orderNo);
        return saasOrder;
    }
    /**
     * 根据流水号查询订单详情
     * @param outTradeNo
     * @return
     */
    public SaasOrder getOutTradeNoOrder(String outTradeNo){
        SaasOrder saasOrder = dao.fetch(SaasOrder.class, Cnd.where("OUT_TRADE_NO", "=", outTradeNo));
        return saasOrder;
    }

    /**
     * 分页查询全部
     * @return
     */
    public TablePageData<SaasOrder> selectOrderPage(SaasOrder saasOrder){
        Pager pager = dao.createPager(saasOrder.getPageNum(), saasOrder.getPageSize());
        saasOrder.setNull();
        String order_no = saasOrder.getOrder_no();
        saasOrder.setOrder_no(null);
        String outTradeNo = saasOrder.getOutTradeNo();
        saasOrder.setOutTradeNo(null);
        Cnd cnd = Cnd.from(dao,saasOrder);
        if (cnd == null){
            cnd = Cnd.where("1","=","1");
        }
        if(StringUtils.isNotBlank(saasOrder.getOrder_no())){
            cnd = cnd.and("order_no","like", saasOrder.getOrder_no()+"%");
        }
        if(StringUtils.isNotBlank(saasOrder.getOutTradeNo())){
            cnd = cnd.and("out_trade_no","like", saasOrder.getOutTradeNo()+"%");
        }
        List query = dao.query(SaasOrder.class, cnd.orderBy("create_time","DESC"), pager);
        pager.setRecordCount(dao.count(SaasOrder.class, cnd));
        TablePageData<SaasOrder> tablePageData = new TablePageData(query, pager);
        return tablePageData;
    }

    /**
     * 检查当前商品是否存在待付款单据
     * @param clientId
     * @param isDelete
     * @param orderStatus
     * @param groupId
     * @return
     */
    public List<SaasOrder> getCheckOutTradeNo(String clientId,int isDelete,int orderStatus,String groupId){
        List<SaasOrder> query = dao.query(SaasOrder.class,
                Cnd.where("tenant_id", "=", clientId)
                        .and("is_delete", "=", isDelete)
                        .and("order_status", "=", orderStatus)
                        .and("pay_status", "=", 1)
                        .and("group_id", "=", groupId));
        return query;
    }


    /**
     * 批量删除
     * @param ids
     * @return
     */
    public int delOrderNos(String[] ids) {
        int clear = dao.clear(SaasOrder.class, Cnd.where("order_no", "in", ids));
        return clear;
    }
    
}
