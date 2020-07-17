package CSCI5308.GroupFormationTool.Answers;

import java.util.List;

public interface IAnswerStudentMapperDB {
    List<AnswerStudentMapper> getSurveyResponsesByCourseID(long courseID);
}
