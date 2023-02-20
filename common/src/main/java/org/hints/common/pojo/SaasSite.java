package org.hints.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.time.LocalDateTime;

@Data
@Table("SAAS_SITE")
public class SaasSite extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Name
    private String site_id;

    private String oracleId;

    @Column(hump = true)
    private String tenantId;

    @Column
    private String title;

    @Column
    private String logo;

    @Column
    private String desa;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private String qq;

    @Column
    private String weixin;

    @Column
    private Long status;

    @Column(hump = true)
    private String closeReason;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private LocalDateTime create_time;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private LocalDateTime modify_time;

    @Column(hump = true)
    private String addressUrl;

    /** 公司名称 */
    @Column
    private String company;
}