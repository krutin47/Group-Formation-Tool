package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Questions.Question;

import java.util.List;

public class Survey {
    private long sId;
    private long sTitle;
    private List<Question> questions;

    public Survey(long sId, long sTitle, String isPublished, List<Question> questions) {
        this.sId = sId;
        this.sTitle = sTitle;
        this.questions = questions;
    }

    public long getsId() {
        return sId;
    }

    public void setsId(long sId) {
        this.sId = sId;
    }

    public long getsTitle() {
        return sTitle;
    }

    public void setsTitle(long sTitle) {
        this.sTitle = sTitle;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
