package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.SystemConfig;

public class QuestionType {
    private long typeID;
    private String type;

    public QuestionType(long typeID, String type) {
        SystemConfig.instance().getLOG().info("In Constructor");
        SystemConfig.instance().getLOG().debug("Checking Parameter typeID :: " + typeID);
        SystemConfig.instance().getLOG().debug("Checking Parameter type :: " + type.isEmpty());
        this.typeID = typeID;
        this.type = type;
    }

    public long getTypeID() {
        return typeID;
    }

    public void setTypeID(long typeID) {
        this.typeID = typeID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
