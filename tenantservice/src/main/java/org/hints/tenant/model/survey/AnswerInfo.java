package org.hints.tenant.model.survey;

/**
 * @Description TODO1
 * @Author 180686
 * @Date 2022/10/10 11:20
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Data
@Table("ANSWER_INFO")
@ApiModel(description = "问卷答案")
public class AnswerInfo
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    @Column
    @ApiModelProperty("用户id")
    private String usid;

    /** 问卷id */
    @Column(hump = true)
    @ApiModelProperty("问卷id")
    private String surveyId;

    /** 问题id */
    @Column(hump = true)
    @ApiModelProperty("问题id")
    private Long questionId;

    /** 选项id */
    @Column(hump = true)
    @ApiModelProperty("选项id")
    private String optionId;

    /** 答案 */
    @Column(hump = true)
    @ApiModelProperty("答案")
    private String optionContent;

}