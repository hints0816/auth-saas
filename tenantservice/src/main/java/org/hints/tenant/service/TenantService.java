package org.hints.tenant.service;

import org.hints.common.pojo.ReturnVo;
import org.hints.common.pojo.SaasTenant;
import org.hints.tenant.model.BeTenantVO;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/3 15:01
 */
public interface TenantService {

    ReturnVo register(SaasTenant saasTenant);

    ReturnVo reset(SaasTenant saasTenant);

    SaasTenant fetchTenantInfo() throws UnsupportedEncodingException;

    String toBeTenant(BeTenantVO beTenantVO);

}
