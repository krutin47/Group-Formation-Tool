package CSCI5308.GroupFormationTool.SurveyRudra;

import CSCI5308.GroupFormationTool.Questions.Question;

import java.util.List;

public class Survey {
    private long surveyID;
    private long surveyTitle;
    private List<Question> questions;

    public Survey(long surveyID, long surveyTitle, String isPublished, List<Question> questions) {
        this.surveyID = surveyID;
        this.surveyTitle = surveyTitle;
        this.questions = questions;
    }

    public long getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(long surveyID) {
        this.surveyID = surveyID;
    }

    public long getSurveyTitle() {
        return surveyTitle;
    }

    public void setSurveyTitle(long surveyTitle) {
        this.surveyTitle = surveyTitle;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
