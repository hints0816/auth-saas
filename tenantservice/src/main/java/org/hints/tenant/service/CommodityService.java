package org.hints.tenant.service;

import org.hints.common.pojo.SaasCommodity;
import org.hints.common.pojo.TablePageData;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/7 18:21
 */
public interface CommodityService {

    int addCommodity(SaasCommodity saasCommodity);

    int updateCommodity(SaasCommodity saasCommodity);

    SaasCommodity fetchCommodityById(String groupId);

    SaasCommodity fetchCommodityByIdOnUser(String groupId);

    TablePageData<SaasCommodity> queryPageCommodity(SaasCommodity saasCommodity);

    TablePageData<SaasCommodity> queryPageCommodityOnUser(SaasCommodity saasCommodity);

}
