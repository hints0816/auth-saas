package org.hints.tenant.service;

import org.hints.common.pojo.SaasOracle;
import org.hints.common.pojo.TablePageData;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/11 9:59
 */
public interface EntityService {

    int updateSaasOracle(SaasOracle saasOracle);

    SaasOracle fetchSaasOracle(String id);

    TablePageData<SaasOracle> querySaasOracleOnUser(SaasOracle saasOracle);

}
