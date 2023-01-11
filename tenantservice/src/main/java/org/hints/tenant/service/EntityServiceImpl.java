package org.hints.tenant.service;

import org.hints.common.pojo.SaasOracle;
import org.hints.common.pojo.TablePageData;
import org.hints.tenant.dao.SaasOracleDao;
import org.hints.tenant.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/11 10:00
 */
@Service
public class EntityServiceImpl implements EntityService {

    @Autowired
    private SaasOracleDao saasOracleDao;

    @Override
    public int updateSaasOracle(SaasOracle saasOracle) {
        SaasOracle updateSaasOracle = saasOracleDao.selectSaasOracleById(saasOracle.getId());
        updateSaasOracle.setMobile(saasOracle.getMobile());
        updateSaasOracle.setEmail(saasOracle.getEmail());
        updateSaasOracle.setUpdate_time(LocalDateTime.now());
        int update = saasOracleDao.updateSaasOracle(updateSaasOracle);
        return update;
    }

    @Override
    public SaasOracle fetchSaasOracle(String id) {
        SaasOracle saasOracle = saasOracleDao.selectSaasOracleById(id);
        return saasOracle;
    }

    @Override
    public TablePageData<SaasOracle> querySaasOracleOnUser(SaasOracle saasOracle) {
        String tenantId = SecurityUtil.getJwtInfo().getUser_id().toString();
        saasOracle.setTenant_id(tenantId);
        TablePageData<SaasOracle> saasOracleTablePageData = saasOracleDao.selectSaasOracleList(saasOracle);
        return saasOracleTablePageData;
    }
}
