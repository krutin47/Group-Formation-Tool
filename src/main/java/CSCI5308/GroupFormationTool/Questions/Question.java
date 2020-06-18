package CSCI5308.GroupFormationTool.Questions;

import java.util.List;

public class Question {
    private long questionID;
    private String questionTitle;
    private String questionText;
    private String creationDate;
    private String questionType;
    private String bannerID;
    List<Choice> choices;

    public Question(){

    }

    public Question(long questionID, String questionTitle, String questionText, String creationDate) {
        this.questionID = questionID;
        this.questionTitle = questionTitle;
        this.questionText = questionText;
        this.creationDate = creationDate;
    }

    public Question(long questionID, String questionTitle, String questionText, String creationDate, String questionType) {
        this.questionID = questionID;
        this.questionTitle = questionTitle;
        this.questionText = questionText;
        this.creationDate = creationDate    ;
        this.questionType = questionType;
    }

    public Question(String questionTitle, String questionText, String questionType, String bannerID, List<Choice> choices) {
        this.questionTitle = questionTitle;
        this.questionText = questionText;
        this.questionType = questionType;
        this.bannerID = bannerID;
        this.choices = choices;
    }

    public long getQuestionID() {
        return questionID;
    }

    public void setQuestionID(long questionID) {
        this.questionID = questionID;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getBannerID() {
        return bannerID;
    }

    public void setBannerID(String bannerID) {
        this.bannerID = bannerID;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}