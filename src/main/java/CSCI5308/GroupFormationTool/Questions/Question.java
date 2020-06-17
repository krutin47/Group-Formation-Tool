package CSCI5308.GroupFormationTool.Questions;

public class Question {
    private long questionID;
    private String questionTitle;
    private String questionText;
    private String creationDate;
    private String questionType;

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
}