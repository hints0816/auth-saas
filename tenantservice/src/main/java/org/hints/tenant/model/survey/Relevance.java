package org.hints.tenant.model.survey;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.List;

/**
 * @Description 问卷逻辑关联
 * @Author 180686
 * @Date 2022/10/9 14:58
 */

@Data
@Table("QUESTION_RELEVANCE")
@ApiModel(description = "问卷逻辑关联")
public class Relevance
{
    @Id
    @ApiModelProperty("id")
    private Long id;

    @Column(hump = true)
    @ApiModelProperty("动作类型")
    private String actionType;

    @Column(hump = true)
    @ApiModelProperty("资源类型")
    private String sourceType;

    @Column(hump = true)
    @ApiModelProperty("当前资源id")
    private Long target;

    @ApiModelProperty("表达式列表")
    private List<Expression> expressionGroup;
}
