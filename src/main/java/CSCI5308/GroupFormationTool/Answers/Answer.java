package CSCI5308.GroupFormationTool.Answers;

public class Answer {

    private long answerID;
    private String answerValue;

    public Answer() {
        //default constructor
    }

    public Answer(long id, String val)
    {
        this.answerID=id;
        this.answerValue=val;
    }
}
