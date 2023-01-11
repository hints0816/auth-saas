package org.hints.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**
 * Created by 180686 on 2021/6/18 14:22
 */

@Data
@NoArgsConstructor
@Table("SAAS_SYS_ORACLE")
public class SaasOracle extends BaseEntity{

    /** ORACLE USERNAME */
    @Name
    private String id;

    /** ORACLE USERNAME */
    @Column(hump = true)
    private String clientId;

    /** 公司名称 */
    @Column(hump = true)
    private String name;

    /** 手机号 */
    @Column
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1[0-9]{10}$", message = "手机号不合法")
    private String mobile;

    /** 密码（暂不编码） */
    @Column
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[\\s\\S]{8,16}$", message = "密码不合法")
    private String password;

    /** 邮箱 */
    @Column
    @NotBlank(message = "Email不能为空")
    @Email(message = "Email格式不正确")
    private String email;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private LocalDateTime create_time;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private LocalDateTime update_time;

    /** 状态 */
    @Column
    private Long status;

    /** 已创建站点数量 */
    @Column(hump = true)
    private Long siteNum;

    /** 租户ID */
    @Column(hump = true)
    private String tenant_id;

    /** 最大站点数量 */
    @Column(hump = true)
    private Long maxSiteNum;

    /** 套餐ID */
    @Column(hump = true)
    private String groupId;

    /** 到期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(hump = true)
    private LocalDateTime expireTime;

    /** 主域名 */
    @Column
    private String domain;

}
