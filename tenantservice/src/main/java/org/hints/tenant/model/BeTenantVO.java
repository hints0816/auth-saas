package org.hints.tenant.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/10 18:06
 */
@Data
public class BeTenantVO {

    private String tenantid;
    private String groupId;
    private LocalDateTime plus;
    private Long maxSiteNum;
    private String addon;
}
