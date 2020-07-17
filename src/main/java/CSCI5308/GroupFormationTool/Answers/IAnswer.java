package CSCI5308.GroupFormationTool.Answers;

public interface IAnswer {

    public boolean createAnswer(Answer answer,long surveyID,long QuestionID,long userID);
    public String loadAnswerbyID(long ID);

}
