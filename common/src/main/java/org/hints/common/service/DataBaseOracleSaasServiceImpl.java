package org.hints.common.service;

import org.hints.common.pojo.SaasOracle;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/3 15:25
 */
@Service
public class DataBaseOracleSaasServiceImpl implements DataBaseSaasService {

    @Autowired
    private Dao dao;

    @Override
    public List<SaasOracle> getDbInfoWithSaas() {
        Sql sql = Sqls.create("SELECT A.* FROM SAAS_SYS_ORACLE A, DBA_USERS B " +
                "WHERE A.CLIENT_ID=B.USERNAME AND STATUS = @STATUS");
        sql.setParam("STATUS",1);
        sql.setCallback(Sqls.callback.entities());
        sql.setEntity(dao.getEntity(SaasOracle.class));
        List<SaasOracle> list = dao.execute(sql).getList(SaasOracle.class);
        return list;
    }

    @Override
    public SaasOracle selectSaasOracleByClientId(String clientId) {
        SaasOracle fetch = dao.fetch(SaasOracle.class, Cnd.where("client_id","=",clientId));
        return fetch;
    }
}
