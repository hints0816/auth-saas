package org.hints.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.time.LocalDateTime;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/7 18:22
 */
@Data
@NoArgsConstructor
@Table("SAAS_COMMODITY")
public class SaasCommodity extends BaseEntity{

    @Name
    @Column(hump = true)
    private String groupId;

    @Column
    private Integer type;

    @Column(hump = true)
    private String groupName;

    @Column
    private String image;

    @Column(hump = true)
    private String addonArray;

    @Column
    private String unit;

    @Column
    private Long fee;

    @Column
    private String dsca;

    @Column
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private LocalDateTime create_time;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private LocalDateTime modify_time;

}
