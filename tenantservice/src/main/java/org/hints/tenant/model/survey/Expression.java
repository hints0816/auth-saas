package org.hints.tenant.model.survey;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Description 逻辑表达式
 * @Author 180686
 * @Date 2022/10/9 15:01
 */
@Data
@Table("QUESTION_RELATION")
@ApiModel(description = "逻辑表达式")
public class Expression {

    /**
     * 表达式的id
     */
    @Id
    @ApiModelProperty("id")
    private Long id;


    /**
     * 表达式的id
     */
    @Column(hump = true)
    @ApiModelProperty("表达式的id")
    private Long rid;

    /**
     * 操作方式
     */
    @Column(hump = true)
    @ApiModelProperty("操作方式")
    private String operator;

    /**
     * 关联的问题id
     */
    @Column(hump = true)
    @ApiModelProperty("关联的问题id")
    private Long qid;

    /**
     * 选项id
     */
    @Column(hump = true)
    @ApiModelProperty("选项id")
    private Long optionId;

    /**
     * 类型
     */
    @Column(hump = true)
    @ApiModelProperty("类型")
    private Long type;

}
