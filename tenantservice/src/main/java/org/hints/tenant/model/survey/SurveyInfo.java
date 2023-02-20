package org.hints.tenant.model.survey;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2022/10/9 11:10
 */
@Data
@Table("SURVEY_INFO")
@ApiModel(description = "问卷主表")
public class SurveyInfo {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @ApiModelProperty("id")
    private Long id;

    /**
     * 主题
     */
    @Column
    @ApiModelProperty("主题")
    private String them;

    /**
     * 创建人
     */
    @Column(hump = true)
    @ApiModelProperty("创建人")
    private String createBy;

    /**
     * 更新人
     */
    @Column(hump = true)
    @ApiModelProperty("更新人")
    private String updateBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(hump = true)
    @ApiModelProperty("创建时间")
    private Date createDate;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(hump = true)
    @ApiModelProperty("更新时间")
    private Date updateDate;

    /**
     * 状态
     */
    @Column
    @ApiModelProperty("状态")
    private Long state;

    @ApiModelProperty("问题列表")
    private List<QuestionInfo> questionInfoList;

    @ApiModelProperty("关联")
    private List<Relevance> relevanceList;


}