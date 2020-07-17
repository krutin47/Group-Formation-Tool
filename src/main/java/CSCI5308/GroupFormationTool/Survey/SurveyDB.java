package CSCI5308.GroupFormationTool.Survey;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.Role;
import CSCI5308.GroupFormationTool.Database.CallQuery;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Questions.Choice;
import CSCI5308.GroupFormationTool.Questions.Question;

public class SurveyDB implements ISurveyDB{

	@Override
	public boolean createSurvey(String surveytitle, long instructorBannerID, long courseID) {
		CallStoredProcedure callStoredProcedure = null;
		try{
			callStoredProcedure = new CallStoredProcedure("spCreateSurvey(?,?,?)");
			callStoredProcedure.setParameter(1, surveytitle);
			callStoredProcedure.setParameter(2, instructorBannerID);
			callStoredProcedure.setParameter(3, courseID);
			callStoredProcedure.execute();
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}finally {
			if (null != callStoredProcedure)
			{
				callStoredProcedure.cleanup();
			}
		}
		return true;
	}

	@Override
	public boolean publishSurvey(long surveyID) {
		CallStoredProcedure callStoredProcedure = null;
		try{
			callStoredProcedure = new CallStoredProcedure("spPublishSurvey(?)");
			callStoredProcedure.setParameter(1, surveyID);
			callStoredProcedure.execute();
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}finally {
			if (null != callStoredProcedure)
			{
				callStoredProcedure.cleanup();
			}
		}
		return true;
	}

	@Override
	public boolean deleteSurveyQuestion(long questionID, long surveyID) {
		CallStoredProcedure callStoredProcedure = null;
		try{
			callStoredProcedure = new CallStoredProcedure("spDeleteSurveyQuesById(?,?)");
			callStoredProcedure.setParameter(1, questionID);
			callStoredProcedure.setParameter(2, surveyID);
			callStoredProcedure.execute();
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}finally {
			if (null != callStoredProcedure)
			{
				callStoredProcedure.cleanup();
			}
		}
		return true;
	}

	@Override
	public long loadSurveyIdbyCourseID(long cid) {
		long sID=0;
		CallStoredProcedure callStoredProcedure = null;
		try{
			callStoredProcedure = new CallStoredProcedure("spLoadSurveyIDbyCourseID(?)");
			callStoredProcedure.setParameter(1, cid);

			ResultSet results = callStoredProcedure.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					sID=results.getInt("surveyID");

				}
			}

		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			if (null != callStoredProcedure)
			{
				callStoredProcedure.cleanup();
			}
		}
		return sID;
	}

	@Override
	public int checkSurveyExists(long courseID) {
		int countRows=0;
		CallStoredProcedure callStoredProcedure = null;
		try{
			callStoredProcedure = new CallStoredProcedure("spCheckSurveyExists(?)");
			callStoredProcedure.setParameter(1, courseID);
			ResultSet results = callStoredProcedure.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					countRows=results.getInt("count(InstructorSurvey.courseID)");

				}
			}

		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			if (null != callStoredProcedure)
			{
				callStoredProcedure.cleanup();
			}
		}
		System.out.println("count is="+countRows);
		return countRows;
	}

	@Override
	public String checkSurveyIsPublished(long surveyID) {
		String param=null;
		CallStoredProcedure callStoredProcedure = null;
		try{
			callStoredProcedure = new CallStoredProcedure("spCheckifSurveyisPublished(?)");
			callStoredProcedure.setParameter(1, surveyID);

			ResultSet results = callStoredProcedure.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					param=results.getString("isPublished");

				}
			}

		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			if (null != callStoredProcedure)
			{
				callStoredProcedure.cleanup();
			}
		}
		return param;
	}

	@Override
	public int checkifQuestionInSurveyExists(long questionID, long surveyID) {
		int countRows=0;
		CallStoredProcedure callStoredProcedure = null;
		try{
			callStoredProcedure = new CallStoredProcedure("spCheckIfQuestionExistsinSurvey(?,?)");
			callStoredProcedure.setParameter(1, questionID);
			callStoredProcedure.setParameter(2, surveyID);

			ResultSet results = callStoredProcedure.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					countRows=results.getInt("count(questionID)");

				}
			}

		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			if (null != callStoredProcedure)
			{
				callStoredProcedure.cleanup();
			}
		}
		return countRows;
	}

	@Override
	public List<Question> loadQuestionBySurveyID(long surveyID)
	{
		List<Question> questions = new ArrayList<Question>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spLoadQuestionsBySurvey(?)");
			proc.setParameter(1, surveyID);

			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long questionID = results.getLong(1);
					String questionTitle = results.getString(2);
					String questionText = results.getString(3);
					Date creationDate = results.getDate(4);

					Question q = new Question();
					q.setQuestionID(questionID);
					q.setQuestionTitle(questionTitle);
					q.setQuestionText(questionText);
					q.setCreationDate(creationDate);
					questions.add(q);
				}
			}
		}
		catch (SQLException e)
		{
			// Logging needed.
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return questions;
	}


	@Override
	public boolean saveQuestionInSurvey(long id, long courseID) {
		int countRows=0;
		CallStoredProcedure callStoredProcedure = null;
		try{
			callStoredProcedure = new CallStoredProcedure("spSaveQuestionInSurvey(?,?)");
			callStoredProcedure.setParameter(1, id);
			callStoredProcedure.setParameter(2, courseID);
			callStoredProcedure.execute();


		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}finally {
			if (null != callStoredProcedure)
			{
				callStoredProcedure.cleanup();
			}
		}
		return true;
	}

    @Override
    public boolean isPublished(long courseID) {
        CallStoredProcedure proc = null;
        String s=new String();
        try
        {
            proc = new CallStoredProcedure("spCheckSurveyPublished(?)");
            proc.setParameter(1, courseID);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while(results.next())
                {
                    s = results.getString("isPublished");
                }
            }

            if(s.equalsIgnoreCase("y")||s.equalsIgnoreCase("Y"))
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            // Logging needed.
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return false;
    }
}
