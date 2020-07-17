package CSCI5308.GroupFormationTool.Answers;

import java.util.List;

public interface IAnswer {

    public boolean createAnswer(Answer answer,long surveyID,long QuestionID,long userID);
    public String loadAnswerbyID(long ID);
    long getSurveyIDByCourseID (long courseID);
    List<Long> loadListUserAnswered(long surveyID);
    List<Answer> loadAnswersByUserID(long userID);
}