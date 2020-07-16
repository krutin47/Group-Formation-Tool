package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionService implements IQuestion {

    @Override
    public boolean createMCQuestion(Question question) {
        Boolean addQuestion = createSimpleQuestion(question);

        if (!addQuestion){
            return false;
        }

        List<Choice> choices = question.getChoices();
        for (Choice option: choices){
            CallStoredProcedure callStoredProcedure = null;
            try {
                callStoredProcedure = new CallStoredProcedure("spAddOptionForQuestion(?,?,?)");
                callStoredProcedure.setParameter(1, option.getOptionText());
                callStoredProcedure.setParameter(2, option.getOptionValue());
                callStoredProcedure.setParameter(3, question.getQuestionTitle());
                callStoredProcedure.execute();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }finally {
                if (null != callStoredProcedure)
                {
                    callStoredProcedure.cleanup();
                }
            }
        }
        return true;
    }

    @Override
    public boolean createSimpleQuestion(Question question) {
        CallStoredProcedure callStoredProcedure = null;
        try{
            callStoredProcedure = new CallStoredProcedure("spCreateSimpleQuestion(?,?,?,?)");
            callStoredProcedure.setParameter(1, question.getQuestionTitle());
            callStoredProcedure.setParameter(2, question.getQuestionText());
            callStoredProcedure.setParameter(3, question.getQuestionType());
            callStoredProcedure.setParameter(4, question.getBannerID());
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
	public List<Question> loadQuestionByInstID(long id)
	{
		List<Question> questions = new ArrayList<Question>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("sploadQuestionByInstID(?)");
			proc.setParameter(1, id);

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
   	public List<Question> loadQuestionByQID(long id)
   	{
   		List<Question> questions = new ArrayList<Question>();
   		CallStoredProcedure proc = null;
   		try
   		{
   			proc = new CallStoredProcedure("sploadQuestionByQuestionID(?)");
   			proc.setParameter(1, id);

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
    public boolean deleteQuestionById(long id) {
        CallStoredProcedure callStoredProcedure = null;
        try{
            callStoredProcedure = new CallStoredProcedure("spDeleteQuestionById(?)");
            callStoredProcedure.setParameter(1, id);
            
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
    public Question loadQuestionbyQid(long id)
	{
    Question ques=new Question();
    CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spLoadQuestionByQid(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long qid =results.getLong("questionID");
                    String title = results.getString("questionTitle");
                    String text = results.getString("questionText");
                    Date date=results.getDate("creationDate");
                    ques.setQuestionID(qid);
                    ques.setQuestionTitle(title);
                    ques.setQuestionText(text);
                    ques.setCreationDate(date);

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
		return ques;
	}
    
    @Override
    public List<Question> loadInstructorQuestionsList(long courseID)
	{
		List<Question> questions = new ArrayList<Question>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spLoadInstructorQuestionsList(?)");
            proc.setParameter(1, courseID);

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

}
