package org.hints.tenant.service.survey;

import lombok.AllArgsConstructor;
import org.hints.tenant.model.survey.*;
import org.hints.tenant.utils.SecurityUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2022/10/9 11:15
 */
@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class SurveyServiceImpl implements SurveyService {

    private Dao dao;

    @Override
    public SurveyInfo display(Long id, int answer) {
        String username = SecurityUtil.getJwtInfo().getUsername();

        SurveyInfo surveyInfo = dao.fetch(SurveyInfo.class, id);
        List<QuestionInfo> questionInfo = dao.query(QuestionInfo.class, Cnd.where("survey_id", "=", id));
        ArrayList<Relevance> relevances = new ArrayList<>();

        for (QuestionInfo info : questionInfo) {
            List<OptionInfo> optionInfos = dao.query(OptionInfo.class, Cnd.where("question_id", "=", info.getId()));

            Cnd cnd = Cnd.where("usid", "=", username)
                    .and("survey_id", "=", id)
                    .and("question_id", "=", info.getId());
            AnswerInfo answerInfo = dao.fetch(AnswerInfo.class, cnd);
            if (answer == 1) {
                if (answerInfo != null) {
                    info.setAnswer(answerInfo.getOptionId() == null ? answerInfo.getOptionContent() : String.valueOf(answerInfo.getOptionId()));
                }
            }

            info.setOptionInfos(optionInfos);

            List<Relevance> relevanceList = dao.query(Relevance.class, Cnd.where("target", "=", info.getId()));
            for (Relevance relevance : relevanceList) {
                List<Expression> expressionList = dao.query(Expression.class, Cnd.where("rid", "=", relevance.getId()));
                relevance.setExpressionGroup(expressionList);
            }

            relevances.addAll(relevanceList);
        }

        surveyInfo.setQuestionInfoList(questionInfo);
        surveyInfo.setRelevanceList(relevances);
        return surveyInfo;
    }

    @Override
    public SurveyInfo display1(Long id, String auid, int answer) {

        SurveyInfo surveyInfo = dao.fetch(SurveyInfo.class, id);
        List<QuestionInfo> questionInfo = dao.query(QuestionInfo.class, Cnd.where("survey_id", "=", id));
        ArrayList<Relevance> relevances = new ArrayList<>();

        for (QuestionInfo info : questionInfo) {
            List<OptionInfo> optionInfos = dao.query(OptionInfo.class, Cnd.where("question_id", "=", info.getId()));

            Cnd cnd = Cnd.where("usid", "=", auid)
                    .and("survey_id", "=", id)
                    .and("question_id", "=", info.getId());
            AnswerInfo answerInfo = dao.fetch(AnswerInfo.class, cnd);
            if (answer == 1) {
                if (answerInfo != null) {
                    info.setAnswer(answerInfo.getOptionId() == null ? answerInfo.getOptionContent() : String.valueOf(answerInfo.getOptionId()));
                }
            }

            info.setOptionInfos(optionInfos);

            List<Relevance> relevanceList = dao.query(Relevance.class, Cnd.where("target", "=", info.getId()));
            for (Relevance relevance : relevanceList) {
                List<Expression> expressionList = dao.query(Expression.class, Cnd.where("rid", "=", relevance.getId()));
                relevance.setExpressionGroup(expressionList);
            }

            relevances.addAll(relevanceList);
        }

        surveyInfo.setQuestionInfoList(questionInfo);
        surveyInfo.setRelevanceList(relevances);
        return surveyInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int answer(SurveyInfo surveyInfo) {
        String username = SecurityUtil.getJwtInfo().getUsername();
        String surveyId = String.valueOf(surveyInfo.getId());
        List<QuestionInfo> questionInfoList = surveyInfo.getQuestionInfoList();
        int clear = dao.clear(AnswerInfo.class, Cnd.where("SURVEY_ID", "=", surveyId).and("USID", "=", username));
        int i = 0;
        for (QuestionInfo questionInfo : questionInfoList) {
            AnswerInfo answerInfo = new AnswerInfo();
            String answer = questionInfo.getAnswer();
            Long questionType = questionInfo.getQuestionType();
            if(questionType == 4L){
                answerInfo.setOptionContent(answer);
            }else{
                answerInfo.setOptionId(answer);
            }
            answerInfo.setSurveyId(surveyId);
            answerInfo.setUsid(username);
            answerInfo.setQuestionId(questionInfo.getId());
            i += dao.insert(answerInfo) != null ? 1 : 0;
        }
        return i;
    }
}
