package org.hints.tenant.dao;

import org.hints.common.pojo.SaasOracle;
import org.hints.common.pojo.TablePageData;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2022/12/27 18:01
 */
@Repository
public class SaasOracleDao {

    @Autowired
    private Dao dao;

    public SaasOracle insertSaasOracle(SaasOracle saasOracle){
        SaasOracle insert = dao.insert(saasOracle);
        return insert;
    }

    public SaasOracle selectSaasOracleById(String id){
        SaasOracle fetch = dao.fetch(SaasOracle.class, id);
        return fetch;
    }

    public List<SaasOracle> querySaasOracleByIdAndTenantId(String tenantId, String id){
        List<SaasOracle> saasOracles = dao.query(SaasOracle.class,
                Cnd.where("TENANT_ID", "=", tenantId)
                        .and("ID","=",id));
        return saasOracles;
    }


    public int updateSaasOracle(SaasOracle saasOracle){
        int update = dao.update(saasOracle);
        return update;
    }

    public TablePageData<SaasOracle> selectSaasOracleList(SaasOracle saasOracle){
        Pager pager = dao.createPager(saasOracle.getPageNum(), saasOracle.getPageSize());
        saasOracle.setNull();
        Cnd cnd = Cnd.from(dao,saasOracle);
        List query = dao.query(SaasOracle.class, cnd, pager);
        pager.setRecordCount(dao.count(SaasOracle.class, cnd));
        TablePageData<SaasOracle> tablePageData = new TablePageData(query, pager);
        return tablePageData;
    }

    public int updateSiteNumSaasOracle(String id){
        int update = dao.update(SaasOracle.class, Chain.makeSpecial("site_num",
                "+1").add("domain", "http://user.scmaction.gree.com/#/login/"+id), Cnd.where("id","=", id));
        return update;
    }

}
