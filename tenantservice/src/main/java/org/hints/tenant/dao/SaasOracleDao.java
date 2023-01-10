package org.hints.tenant.dao;

import org.hints.common.pojo.SaasOracle;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
