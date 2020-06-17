package CSCI5308.GroupFormationTool.Questions;

public class Options {
    private long optionID;
    private String optionText;
    private int optionValue;
    private long questionID;

    public Options(long optionID, String optionText, int optionValue) {
        this.optionID = optionID;
        this.optionText = optionText;
        this.optionValue = optionValue;
    }

    public Options(long optionID, String optionText, int optionValue, long questionID) {
        this.optionID = optionID;
        this.optionText = optionText;
        this.optionValue = optionValue;
        this.questionID = questionID;
    }

    public long getOptionID() {
        return optionID;
    }

    public void setOptionID(long optionID) {
        this.optionID = optionID;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public int getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(int optionValue) {
        this.optionValue = optionValue;
    }

    public long getQuestionID() {
        return questionID;
    }

    public void setQuestionID(long questionID) {
        this.questionID = questionID;
    }
}
