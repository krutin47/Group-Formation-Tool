package CSCI5308.GroupFormationTool.Questions;

public class QuestionType {
    private long typeID;
    private String type;

    public QuestionType(long typeID, String type) {
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
