package org.hints.tenant.service.survey;


import org.hints.tenant.model.survey.SurveyInfo;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2022/10/9 11:15
 */
public interface SurveyService {

    SurveyInfo display(Long id, int answer);

    SurveyInfo display1(Long id, String auid, int answer);

    int answer(SurveyInfo surveyInfo);
}
