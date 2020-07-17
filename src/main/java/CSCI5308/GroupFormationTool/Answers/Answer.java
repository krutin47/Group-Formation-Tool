package CSCI5308.GroupFormationTool.Answers;

public class Answer {

    private long questionID;
    private String answerValue;
    private long answerID;


    public Answer() {
        //default constructor
    }

    public Answer(String val) {
        this.answerValue = val;
    }

    public Answer(long questionID, String answerValue) {
        this.questionID = questionID;
        this.answerValue = answerValue;
    }

    public long getQuestionID() {
        return questionID;
    }

    public void setQuestionID(long questionID) {
        this.questionID = questionID;
    }

    public long getAnswerID() {
        return answerID;
    }

    public void setAnswerID(long answerID) {
        this.answerID = answerID;
    }

    public String getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(String answerValue) {
        this.answerValue = answerValue;
    }
}

