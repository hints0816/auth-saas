package org.hints.common.service;

import org.hints.common.pojo.SaasTenant;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 180686 on 2021/3/17 9:37
 */

@Service
public class SysUserSaasServiceImpl implements SysUserSaasService {

    @Autowired
    private Dao dao;

    @Override
    public SaasTenant findByUserName(String sys_uid) {
        SaasTenant saasTenant = dao.fetch(SaasTenant.class, Cnd.where("sys_uid","=",sys_uid));
        return saasTenant;
    }

    @Override
    public SaasTenant findByMobile(String mobile) {
        SaasTenant saasTenant = dao.fetch(SaasTenant.class, Cnd.where("mobile","=",mobile));
        return saasTenant;
    }
}

