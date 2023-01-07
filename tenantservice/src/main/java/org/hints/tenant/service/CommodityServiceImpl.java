package org.hints.tenant.service;

import org.hints.common.pojo.SaasCommodity;
import org.hints.common.pojo.TablePageData;
import org.hints.tenant.dao.SaasCommodityDao;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/7 18:21
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private SaasCommodityDao saasCommodityDao;

    @Override
    public int addCommodity(SaasCommodity saasCommodity) {
        return 0;
    }

    @Override
    public int updateCommodity(SaasCommodity saasCommodity) {
        return 0;
    }

    @Override
    public SaasCommodity fetchCommodityById(String groupId) {
        return null;
    }

    @Override
    public SaasCommodity fetchCommodityByIdOnUser(String groupId) {
        SaasCommodity groupIdCommodity = saasCommodityDao.getGroupIdCommodity(groupId);
        return groupIdCommodity.getStatus() == 1 ? null : groupIdCommodity;
    }

    @Override
    public TablePageData<SaasCommodity> queryPageCommodity(SaasCommodity saasCommodity) {
        return null;
    }

    @Override
    public TablePageData<SaasCommodity> queryPageCommodityOnUser(SaasCommodity saasCommodity) {
        saasCommodity.setStatus(1);
        TablePageData<SaasCommodity> tablePageData = saasCommodityDao.selectCommodityPage(saasCommodity);
        return tablePageData;
    }
}
