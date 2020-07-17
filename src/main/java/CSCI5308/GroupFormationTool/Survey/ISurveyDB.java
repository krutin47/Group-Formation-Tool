package CSCI5308.GroupFormationTool.Survey;

import java.util.List;

import CSCI5308.GroupFormationTool.Questions.Question;

public interface ISurveyDB {
	public boolean createSurvey(String survey, long instructorBannerID, long courseID);
	public int checkSurveyExists(long courseID);
	public boolean saveQuestionInSurvey(long id, long courseID);
	public long loadSurveyIdbyCourseID(long courseID);
	public List<Question> loadQuestionBySurveyID(long surveyID);
	public int checkifQuestionInSurveyExists(long questionID, long surveyID);
	public boolean publishSurvey(long surveyID);
	public String checkSurveyIsPublished(long surveyID);
	public boolean deleteSurveyQuestion(long questionID, long surveyID);
    boolean isPublished(long courseID);
}
