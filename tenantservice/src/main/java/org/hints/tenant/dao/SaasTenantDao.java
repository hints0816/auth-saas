package org.hints.tenant.dao;

import org.hints.common.pojo.SaasTenant;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    public SaasTenant selectSaasSysTenantById(String tenantId){
        SaasTenant fetch = dao.fetch(SaasTenant.class, tenantId);
        return fetch;
    }

}
