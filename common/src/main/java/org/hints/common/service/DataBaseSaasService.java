package org.hints.common.service;

import org.hints.common.pojo.SaasOracle;
import org.nutz.dao.entity.Record;

import java.util.List;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/3 15:25
 */
public interface DataBaseSaasService {

    List<SaasOracle> getDbInfoWithSaas();

    SaasOracle selectSaasOracleByClientId(String clientId);
}
