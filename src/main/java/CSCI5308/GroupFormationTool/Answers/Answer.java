package CSCI5308.GroupFormationTool.Answers;

public class Answer {

    private String answerValue;
    private long answerID;


    public Answer() {
        //default constructor
    }

    public Answer(String val) {

        this.answerValue = val;

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

