package CSCI5308.GroupFormationTool.Answers;

import java.util.List;

public class AnswerStudentMapper {
    private long userID;
    private List<Answer> answers;

    public AnswerStudentMapper(long userID, List<Answer> answers) {
        this.userID = userID;
        this.answers = answers;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
