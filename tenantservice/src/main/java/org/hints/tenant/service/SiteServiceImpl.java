package org.hints.tenant.service;

import org.hints.common.config.DataSourceContext;
import org.hints.common.config.MultiRouteDataSource;
import org.hints.common.pojo.ReturnVo;
import org.hints.common.pojo.SaasOracle;
import org.hints.common.pojo.SaasSite;
import org.hints.common.pojo.TablePageData;
import org.hints.common.utils.UUIDUtil;
import org.hints.tenant.dao.SaasOracleDao;
import org.hints.tenant.dao.SaasSiteDao;
import org.hints.tenant.utils.SecurityUtil;
import org.nutz.dao.Chain;
import org.nutz.dao.Dao;
import org.nutz.dao.TableName;
import org.nutz.dao.impl.FileSqlManager;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/11 16:52
 */
@Service
public class SiteServiceImpl implements SiteService {
    @Autowired
    Dao dao;

    @Autowired
    SaasOracleDao saasOracleDao;

    @Autowired
    SaasSiteDao saasSiteDao;

    @Autowired
    private MultiRouteDataSource dataSource;

    @Override
    public ReturnVo createSite(SaasSite saasSite) {
        String tenantId = SecurityUtil.getJwtInfo().getUser_id().toString();

        SaasOracle saasOracle = saasOracleDao.selectSaasOracleById(saasSite.getOracleId());
        if (saasOracle.getSiteNum() >= saasOracle.getMaxSiteNum()) {
            return ReturnVo.error();
        }

        /*生成站点uuid*/
        String siteId = UUIDUtil.getNumberUUID();
        int i = saasOracleDao.updateSiteNumSaasOracle(saasSite.getOracleId());

        /*Saas系统：添加站点基本信息*/
        DataSourceContext.setDBType(saasOracle.getClientId());
        saasSite.setCreate_time(LocalDateTime.now());
        saasSite.setSite_id(siteId);
        saasSite.setStatus(1L);
        saasSite.setTenantId(tenantId);
        saasSiteDao.insertSaasSite(saasSite);

        return ReturnVo.success(siteId);
    }

    @Override
    public int updateSite(SaasSite saasSite) {
        String tenantId = SecurityUtil.getJwtInfo().getUser_id().toString();
        SaasOracle saasOracle = saasOracleDao.selectSaasOracleById(saasSite.getTenantId());

        DataSourceContext.setDBType(saasOracle.getClientId());
        SaasSite saasSiteInfo = saasSiteDao.selectSaasSiteById(saasSite.getSite_id());
        saasSiteInfo.setTitle(saasSite.getTitle());
        saasSiteInfo.setAddress(saasSite.getAddress());
        saasSiteInfo.setDesa(saasSite.getDesa());
        saasSiteInfo.setPhone(saasSite.getPhone());
        saasSiteInfo.setQq(saasSite.getQq());
        saasSiteInfo.setWeixin(saasSite.getWeixin());
        saasSiteInfo.setCompany(saasSite.getCompany());
        saasSiteInfo.setModify_time(LocalDateTime.now());
        saasSiteInfo.setLogo(saasSite.getLogo());

        return saasSiteDao.updateSaasSite(saasSiteInfo);
    }

    @Override
    public int updateStatus(SaasSite saasSite) {
        String tenantId = SecurityUtil.getJwtInfo().getUser_id().toString();
        SaasOracle saasOracle = saasOracleDao.selectSaasOracleById(saasSite.getTenantId());

        DataSourceContext.setDBType(saasOracle.getClientId());
        return saasSiteDao.updateSaasSiteStatus(saasSite.getSite_id(), saasSite.getStatus());
    }

    @Override
    public int deleteSiteByIdsOnUser(String id, String[] siteId) {
        String tenantId = SecurityUtil.getJwtInfo().getUser_id().toString();
        SaasOracle saasOracle = saasOracleDao.selectSaasOracleById(id);
        DataSourceContext.setDBType(saasOracle.getClientId());
        return saasSiteDao.deleteSaasSiteByIds(siteId);
    }

    @Override
    public int closeSite(String id, String siteId) {
        String tenantId = SecurityUtil.getJwtInfo().getUser_id().toString();
        SaasOracle saasOracle = saasOracleDao.selectSaasOracleById(id);
        DataSourceContext.setDBType(saasOracle.getClientId());
        SaasSite saasSite = saasSiteDao.selectSaasSiteById(siteId);
        saasSite.setStatus(3L);
        return saasSiteDao.updateSaasSite(saasSite);
    }

    @Override
    public TablePageData<SaasSite> selectSiteListOnUser(SaasSite saasSite) {
        String tenantId = SecurityUtil.getJwtInfo().getUser_id().toString();
        List<SaasOracle> saasOracles = saasOracleDao.querySaasOracleByIdAndTenantId(tenantId, saasSite.getOracleId());
        if (saasOracles.size() == 0) {
            return null;
        }
        SaasOracle saasOracle = saasOracleDao.selectSaasOracleById(saasSite.getOracleId());
        DataSourceContext.setDBType(saasOracle.getClientId());
        TablePageData tablePageData = saasSiteDao.selectSaasSiteList(saasSite);
        return tablePageData;
    }

    @Override
    public SaasSite selectSiteById(String oraId, String siteId) {
        String tenantId = SecurityUtil.getJwtInfo().getUser_id().toString();
        List<SaasOracle> saasOracles = saasOracleDao.querySaasOracleByIdAndTenantId(tenantId, oraId);
        if (saasOracles.size() == 0) {
            return null;
        }
        SaasOracle saasOracle = saasOracleDao.selectSaasOracleById(oraId);
        DataSourceContext.setDBType(saasOracle.getClientId());
        SaasSite saasSite = saasSiteDao.selectSaasSiteById(siteId);
        return saasSite;
    }

}
