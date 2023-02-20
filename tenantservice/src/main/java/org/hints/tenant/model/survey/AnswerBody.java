package org.hints.tenant.model.survey;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description 回答Body
 * @Author 180686
 * @Date 2022/10/10 11:32
 */
@Data
@ApiModel(description = "回答请求体")
public class AnswerBody {

    @ApiModelProperty("问卷id")
    private String surveyId;

    @ApiModelProperty("答案列表")
    private List<AnswerInfo> answerInfoList;
}
