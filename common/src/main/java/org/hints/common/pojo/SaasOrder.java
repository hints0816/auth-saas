package org.hints.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table("SAAS_SYS_ORDER")
public class SaasOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Name
    private String order_no;

    @Column(hump = true)
    private String tenantId;

    @Column(hump = true)
    private String outTradeNo;

    @Column(hump = true)
    private String groupId;

    @Column(hump = true)
    private Long groupPeriodNum;

    @Column(hump = true)
    private String groupPeriodUnit;

    @Column(hump = true)
    private Integer isDelete;

    @Column(hump = true)
    private String payCert;

    @Column(hump = true)
    private String payCertex;

    @Column(hump = true)
    private Integer orderStatus;

    @Column(hump = true)
    private Integer orderType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(hump = true)
    private LocalDateTime payTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private LocalDateTime create_time;

    @Column(hump = true)
    private Integer payType;

    @Column(hump = true)
    private BigDecimal orderMoney;

    @Column(hump = true)
    private Integer payStatus;

}