package org.hints.tenant.model.survey;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.time.LocalDateTime;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2022/10/9 11:12
 */

@Data
@Table("OPTION_INFO")
@ApiModel(description = "问卷选项")
public class OptionInfo
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Name
    @ApiModelProperty("id")
    private String id;

    /** 问题id */
    @Column(hump = true)
    @ApiModelProperty("问题id")
    private Long questionId;

    /** 选项名称 */
    @Column(hump = true)
    @ApiModelProperty("选项名称")
    private String optionName;

    /** 选项顺序 */
    @Column(hump = true)
    @ApiModelProperty("选项顺序")
    private Long optionSort;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(hump = true)
    @ApiModelProperty("创建时间")
    private LocalDateTime requiredFlag;

}