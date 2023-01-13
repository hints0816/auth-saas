package org.hints.tenant.service;

import org.hints.common.config.DataSourceContext;
import org.hints.common.pojo.ReturnVo;
import org.hints.common.pojo.SaasSite;
import org.hints.tenant.dao.SaasSiteDao;
import org.hints.tenant.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/13 15:03
 */
@Service
public class UserBaseServiceImpl implements UserBaseService{

    @Autowired
    private SaasSiteDao saasSiteDao;

    @Override
    public ReturnVo compList() {
        String clientId = SecurityUtil.getClientId();
        DataSourceContext.setDBType(clientId);
        List<SaasSite> saasSites = saasSiteDao.selectSaasSite();
        List<String> collect = saasSites.stream().map(v -> v.getTitle()).collect(Collectors.toList());
        return ReturnVo.success(collect);
    }
}
