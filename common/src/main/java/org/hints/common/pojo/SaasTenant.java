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

@Data
@NoArgsConstructor
@Table("SAAS_SYS_TENANT")
public class SaasTenant {

    @Name
    private String tenant_id;

    @Column(hump = true)
    private String sysUid;

    @Column
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1[0-9]{10}$", message = "手机号不合法")
    private String mobile;

    @Column
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[\\s\\S]{8,16}$", message = "密码不合法")
    private String password;

    @Column
    @NotBlank(message = "Email不能为空")
    @Email(message = "Email格式不正确")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private LocalDateTime create_time;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private LocalDateTime update_time;

    @Column
    private Long status;

}
