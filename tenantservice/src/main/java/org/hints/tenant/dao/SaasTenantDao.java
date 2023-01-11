package org.hints.tenant.dao;

import org.apache.commons.lang3.StringUtils;
import org.hints.common.pojo.SaasTenant;
import org.hints.common.pojo.TablePageData;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SaasTenantDao {

    @Autowired
    private Dao dao;

    public SaasTenant selectSaasSysTenantByMobile(String mobile) {
        SaasTenant fetch = dao.fetch(SaasTenant.class, Cnd.where("mobile", "=", mobile));
        return fetch;
    }

    public int updateSaasTenantPassword(String mobile, String password) {
        int update = dao.update(SaasTenant.class,
                Chain.make("password", password),
                Cnd.where("mobile", "=", mobile));
        return update;
    }

    public SaasTenant selectSaasSysTenantById(String tenantId) {
        SaasTenant fetch = dao.fetch(SaasTenant.class, tenantId);
        return fetch;
    }

    public TablePageData<SaasTenant> selectSaasSysTenantList(SaasTenant saasTenant) {
        Pager pager = dao.createPager(saasTenant.getPageNum(), saasTenant.getPageSize());
        saasTenant.setNull();
        String sysUid = saasTenant.getSysUid();
        saasTenant.setSysUid(null);
        String mobile = saasTenant.getMobile();
        saasTenant.setMobile(null);
        String email = saasTenant.getEmail();
        saasTenant.setEmail(null);
        Cnd cnd = Cnd.from(dao, saasTenant);
        if (cnd == null) {
            cnd = Cnd.NEW();
        }
        if (StringUtils.isNotBlank(sysUid)) {
            cnd = cnd.and("sys_uid", "like", sysUid + "%");
        }
        if (StringUtils.isNotBlank(mobile)) {
            cnd = cnd.and("mobile", "like", mobile + "%");
        }
        if (StringUtils.isNotBlank(email)) {
            cnd = cnd.and("email", "like", email + "%");
        }
        List query = dao.query(SaasTenant.class, cnd, pager);
        pager.setRecordCount(dao.count(SaasTenant.class, cnd));
        TablePageData<SaasTenant> tablePageData = new TablePageData(query, pager);
        return tablePageData;
    }

    public int deleteSaasSysTenantByIds(Long[] tenantIds){
        int delete = dao.clear(SaasTenant.class, Cnd.where("tenant_id", "in", tenantIds));
        return delete;
    }

    public int stopSaasSysTenant(Long[] tenant_ids){
        int update = dao.update(SaasTenant.class,
                Chain.make("STATUS", 2),
                Cnd.where("TENANT_ID", "IN", tenant_ids));
        return update;
    }
}
