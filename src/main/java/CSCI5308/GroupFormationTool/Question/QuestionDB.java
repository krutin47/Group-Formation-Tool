package CSCI5308.GroupFormationTool.Question;
import CSCI5308.GroupFormationTool.Database.CallQuery;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDB implements IQuestionPersistence{


	//Load all questions in DB
	public List<Question> loadAllQuestionfromDB()
	{

		List<Question> questions = new ArrayList<Question>();
		CallQuery callquery = null;
		String query2="Select questionID, questionTitle, questionText, creationDate\r\n" + 
				"FROM Question \r\n" + 
				"where questionID IN (Select questionID from InstructorQuestionMapper where instructorID='7');";


		String query="SELECT questionID, questionTitle, questionText, creationDate\r\n" + 
				"    FROM Question\r\n" + 
				"    ORDER BY questionID ASC;";
		try
		{

			callquery = new CallQuery(query);

			ResultSet results = callquery.executeWithResults(query);
			if (null != results)
			{
				while (results.next())
				{

					long id =results.getLong("questionID");
					String title = results.getString("questionTitle");
					String text = results.getString("questionText");
					Date date=results.getDate("creationDate");
					Question q = new Question();
					q.setQuestionID(id);
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
	public List<Question> loadAllQuestion()
    {

        List<Question> questions = new ArrayList<Question>();
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spLoadAllQuestion()");
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    long id = results.getLong(1);
                    String title = results.getString(2);
                    String text=results.getString(3);
                    Question q = new Question();
                    q.setQuestionID(id);
                    q.setQuestionTitle(title);
                    q.setQuestionText(text);
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
        return questions;   //returns the list of questions found

    }




	

	//Load the question by QuestionID
	public void loadQuestionByID(long id, Question question)
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spFindQuestionByID(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					String title = results.getString(2);   //get the question title
					String text = results.getString(3);   //get the text
					question.setQuestionID(id);   //Set ID
					question.setQuestionTitle(title);   //Set Title
					question.setQuestionText(text);    //Set Text
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

	}
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




	//To create a question in DB
	public boolean createQuestion(Question question)
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spCreateQuestion(?, ?,?,?)");
			proc.setParameter(1, question.getQuestionTitle());
			proc.setParameter(2,question.getQuestionText());
			proc.setParameter(3,question.getTypeID());
			proc.registerOutputParameterLong(4);
			proc.execute();
		}
		catch (SQLException e)
		{
			// Logging needed
			return false;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return true;

	}


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
		try
		{

			callquery4 = new CallQuery(query4);
			callquery4.executeUpdate(query4);


			callquery2 = new CallQuery(query2);
			callquery2.executeUpdate(query2);

			callquery1 = new CallQuery(query1);
			callquery1.executeUpdate(query1);

			callquery3 = new CallQuery(query3);
			System.out.println("query is="+query3);

			callquery3.executeUpdate(query3);


		}
		catch (SQLException e)
		{
			// Logging needed
			return false;
		}
		finally
		{
			if (null != callquery1)
			{
				callquery1.cleanup();
			}
			if (null != callquery2)
			{
				callquery2.cleanup();
			}
			if (null != callquery3)
			{
				callquery3.cleanup();
			}
			if (null != callquery4)
			{
				callquery4.cleanup();
			}
		}
		return true;
	}


	//Delete a question in DB by id
	public boolean deleteQuestion(long id)
	{
		CallStoredProcedure proc = null;

		try
		{
			proc = new CallStoredProcedure("spDeleteQuestion(?)");
			proc.setParameter(1, id);
			proc.execute();
		}
		catch (SQLException e)
		{
			// Logging needed
			return false;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return true;
	}

}

