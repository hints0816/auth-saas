package org.hints.common.service;

import org.hints.common.pojo.SaasTenant;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2022/12/28 11:17
 */
public interface SysUserSaasService {
    SaasTenant findByUserName(String userName);

    SaasTenant findByMobile(String mobile);
}
