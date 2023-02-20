package org.hints.tenant.model.survey;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.List;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2022/10/9 11:12
 */
@Data
@Table("QUESTION_INFO")
@ApiModel(description = "问卷问题")
public class QuestionInfo {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @ApiModelProperty("id")
    private Long id;

    /**
     * 关联id
     */
    @Column(hump = true)
    @ApiModelProperty("关联的问卷id")
    private Long surveyId;

    /**
     * 选项类型 (1:多选；2:单选；3:下拉单选；4:填空)
     */
    @Column(hump = true)
    @ApiModelProperty("选项类型 (1:多选；2:单选；3:下拉单选；4:填空)")
    private Long questionType;

    /**
     * 问题名称
     */
    @Column(hump = true)
    @ApiModelProperty("问题名称")
    private String questionName;

    /**
     * 更新人
     */
    @Column(hump = true)
    @ApiModelProperty("更新人")
    private String questionSort;

    /**
     * 必填
     */
    @Column(hump = true)
    @ApiModelProperty("是否必填")
    private Long requiredFlag;

    /**
     * 图片
     */
    @Column(hump = true)
    @ApiModelProperty("图片")
    private String questionPicId;

    /**
     * 描述
     */
    @Column(hump = true)
    @ApiModelProperty("描述")
    private String questionDescription;

    /**
     * 选项属性 (1:时间；2:手机；3:数字)
     */
    @Column(hump = true)
    @ApiModelProperty("选项属性 (1:时间；2:手机；3:数字)")
    private Long questionAttr;

    @ApiModelProperty("回答")
    private String answer;

    private List<OptionInfo> optionInfos;

}