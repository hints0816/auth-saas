package org.hints.tenant.controller.addon;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.hints.common.pojo.ReturnVo;
import org.hints.tenant.model.survey.SurveyInfo;
import org.hints.tenant.service.survey.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description TODO1
 * @Author 180686
 * @Date 2022/10/9 11:35
 */
@Api(tags = "问卷接口")
@RestController
@RequestMapping("/survey")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class SurveyController {

    private SurveyService surveyService;

    @GetMapping("/{id}")
    @ApiOperation(value = "问卷显示接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "问卷编号", dataType = "Long",required = true),
            @ApiImplicitParam(name = "answer", value = "是否显示回答(0为否;1为是;默认0)", dataType = "Integer")
    })
    public ReturnVo<SurveyInfo> display(@PathVariable Long id, @RequestParam(required = false, defaultValue = "0") int answer){
        SurveyInfo display = surveyService.display(id, answer);

        return ReturnVo.success(display);
    }

    @PostMapping()
    @ApiOperation(value = "问卷回答接口")
    @ApiImplicitParam(name = "answerBody", value = "问卷回答", dataType = "AnswerBody")
    public ReturnVo answer(@RequestBody SurveyInfo surveyInfo){
        return ReturnVo.toAjax(surveyService.answer(surveyInfo));
    }


    @GetMapping("/{id}/{auid}")
    @ApiOperation(value = "问卷显示接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "问卷编号", dataType = "Long",required = true),
            @ApiImplicitParam(name = "auid", value = "用户ID", dataType = "String",required = true),
            @ApiImplicitParam(name = "answer", value = "是否显示回答(0为否;1为是;默认0)", dataType = "Integer")
    })
    public ReturnVo<SurveyInfo> display(@PathVariable Long id, @PathVariable String  auid, @RequestParam(required = false, defaultValue = "0") int answer){
        SurveyInfo display = surveyService.display1(id,auid, answer);

        return ReturnVo.success(display);
    }
}
