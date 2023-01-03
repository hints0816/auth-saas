package org.hints.tenant.service;

import java.time.LocalDateTime;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/3 15:01
 */
public interface TenantService {

    void create(String tenantid, String groupId, LocalDateTime plus, Long maxSiteNum, String addon);
}
