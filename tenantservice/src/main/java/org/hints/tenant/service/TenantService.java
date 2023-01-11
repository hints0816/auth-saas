package org.hints.tenant.service;

import org.hints.common.pojo.ReturnVo;
import org.hints.common.pojo.SaasTenant;
import org.hints.common.pojo.TablePageData;
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

    SaasTenant fetchTenantInfo();

    String toBeTenant(BeTenantVO beTenantVO);

    SaasTenant fetchSaasTenant(String tenant_id);

    TablePageData<SaasTenant> querySaasTenant(SaasTenant saasTenant);

    int stopSaasTenant(Long[] tenant_ids);

    int delSaasTenant(Long[] tenant_ids);

}
