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

}


/*   
	    @Override
	    public List<Question> loadQuestionByInstID(long id)
	    {
	        List<Question> questions = new ArrayList<Question>();
	        CallQuery callquery = null;
	        String query2="Select questionID, questionTitle, questionText, creationDate\r\n" +
	                "FROM Question \r\n" +
	                "where questionID IN (Select questionID from InstructorQuestionMapper where instructorID='"+id+"');";

	        try
	        {
	            callquery = new CallQuery(query2);

	            ResultSet results = callquery.executeWithResults(query2);
	            if (null != results)
	            {
	                while (results.next())
	                {


	                    long qid =results.getLong("questionID");
	                    String title = results.getString("questionTitle");
	                    String text = results.getString("questionText");
	                    Date date=results.getDate("creationDate");
	                    Question q = new Question();
	                    q.setQuestionID(qid);
	                    q.setQuestionTitle(title);
	                    q.setQuestionText(text);
	                    q.setCreationDate(date);

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
	            if (null != callquery)
	            {
	                callquery.cleanup();
	            }
	        }
	        return questions;   //returns the list of questions found


	    }

	    @Override
	    public List<Question> loadQuestionByQID(long id)
	    {
	        List<Question> questions = new ArrayList<Question>();
	        CallQuery callquery = null;
	        String query2="Select questionID, questionTitle, questionText, creationDate\r\n" +
	                "FROM Question \r\n" +
	                "where questionID='"+id+"';";

	        try
	        {
	            callquery = new CallQuery(query2);

	            ResultSet results = callquery.executeWithResults(query2);
	            if (null != results)
	            {
	                while (results.next())
	                {


	                    long qid =results.getLong("questionID");
	                    String title = results.getString("questionTitle");
	                    String text = results.getString("questionText");
	                    Date date=results.getDate("creationDate");
	                    Question q = new Question();
	                    q.setQuestionID(qid);
	                    q.setQuestionTitle(title);
	                    q.setQuestionText(text);
	                    q.setCreationDate(date);

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
	            if (null != callquery)
	            {
	                callquery.cleanup();
	            }
	        }
	        return questions;   //returns the list of questions found
	    }

	    @Override
	    public boolean deleteQuestionById(long id)
	    {
	        CallQuery callquery1 = null;
	        CallQuery callquery2 = null;

	        CallQuery callquery3 = null;
	        CallQuery callquery4 = null;

	        String query3=" DELETE FROM Question\r\n" +
	                "    WHERE Question.questionID = '"+id+"';";
	        String query1="DELETE FROM QuestionOption\r\n" +
	                "    WHERE QuestionOption.questionID = '"+id+"';";
	        String query2="DELETE FROM QuestionTypeMapper\r\n" +
	                "    WHERE QuestionTypeMapper.questionID = '"+id+"';";
	        String query4="DELETE FROM InstructorQuestionMapper\r\n" +
	                "    WHERE InstructorQuestionMapper.questionID = '"+id+"';";
	        try{
	            callquery4 = new CallQuery(query4);
	            callquery4.executeUpdate(query4);

	            callquery2 = new CallQuery(query2);
	            callquery2.executeUpdate(query2);

	            callquery1 = new CallQuery(query1);
	            callquery1.executeUpdate(query1);

	            callquery3 = new CallQuery(query3);
	            System.out.println("query is="+query3);

	            callquery3.executeUpdate(query3);

	        }catch (SQLException e) {
	            // Logging needed
	            return false;
	        }finally {
	            if (null != callquery1) {
	                callquery1.cleanup();
	            }

	            if (null != callquery2) {
	                callquery2.cleanup();
	            }

	            if (null != callquery3) {
	                callquery3.cleanup();
	            }

	            if (null != callquery4) {
	                callquery4.cleanup();
	            }
	        }
	        return true;
	    }

 */

