package CSCI5308.GroupFormationTool.Answers;

import java.util.ArrayList;
import java.util.List;

public class AnswerStudentDB implements IAnswerStudentMapperDB{
    IAnswer answerDB = new AnswerDB();
    IStudentSurvey studentSurvey = new StudentSurveyDB();

    @Override
    public List<AnswerStudentMapper> getSurveyResponsesByCourseID(long courseID) {
        List<AnswerStudentMapper> answerStudent = new ArrayList<>();
        long surveyID = studentSurvey.getSurveyIDfromCourse(courseID);
        List<Long> userID;
        userID = answerDB.loadListUserAnswered(surveyID);

        for(long uID: userID){
            answerStudent.add(new AnswerStudentMapper(uID, answerDB.loadAnswersByUserID(uID)));
        }

        return answerStudent;
    }
}
