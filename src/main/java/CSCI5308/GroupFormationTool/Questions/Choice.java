package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.SystemConfig;

public class Choice {
    private long optionID;
    private String optionText;
    private int optionValue;
    private long questionID;

    public Choice() {

    }

    public Choice(String optionText, int optionValue) {
        SystemConfig.instance().getLOG().info("In Constructor with 2 parameters");
        SystemConfig.instance().getLOG().debug("Checking Parameter optionText :: " + optionText.isEmpty());
        SystemConfig.instance().getLOG().debug("Checking Parameter optionValue :: " + optionValue);

        this.optionText = optionText;
        this.optionValue = optionValue;
    }

    public Choice(long optionID, String optionText, int optionValue) {
        SystemConfig.instance().getLOG().info("In Constructor with 3 parameters");
        SystemConfig.instance().getLOG().debug("Checking Parameter optionID :: " + optionID);
        SystemConfig.instance().getLOG().debug("Checking Parameter optionText :: " + optionText.isEmpty());
        SystemConfig.instance().getLOG().debug("Checking Parameter optionValue :: " + optionValue);

        this.optionID = optionID;
        this.optionText = optionText;
        this.optionValue = optionValue;
    }

    public Choice(long optionID, String optionText, int optionValue, long questionID) {
        SystemConfig.instance().getLOG().info("In Constructor with 4 parameters");
        SystemConfig.instance().getLOG().debug("Checking Parameter optionID :: " + optionID);
        SystemConfig.instance().getLOG().debug("Checking Parameter optionText :: " + optionText.isEmpty());
        SystemConfig.instance().getLOG().debug("Checking Parameter optionValue :: " + optionValue);
        SystemConfig.instance().getLOG().debug("Checking Parameter questionID :: " + questionID);

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
