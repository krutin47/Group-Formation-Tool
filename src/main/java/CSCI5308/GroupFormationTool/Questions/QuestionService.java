package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Database.CallQuery;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.slf4j.Logger;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionService implements IQuestion {

    private Logger LOG;

    @Override
    public boolean createMCQuestion(Question question) {
        LOG = SystemConfig.instance().getLOG();
        LOG.info("In createMCQuestion method");

        boolean addQuestion = createSimpleQuestion(question);
        LOG.debug("Checking Question Added :: " + addQuestion);

        if (!addQuestion){
            return false;
        }

        List<Choice> choices = question.getChoices();
        for (Choice option: choices){
            LOG.info("Calling Stored Procedure");
            CallStoredProcedure callStoredProcedure = null;
            try {
                callStoredProcedure = new CallStoredProcedure("spAddOptionForQuestion(?,?,?)");
                callStoredProcedure.setParameter(1, option.getOptionText());
                callStoredProcedure.setParameter(2, option.getOptionValue());
                callStoredProcedure.setParameter(3, question.getQuestionTitle());
                callStoredProcedure.execute();
            } catch (SQLException e) {
                LOG.error("sql query exception", e);
                e.printStackTrace();
                return false;
            }finally {
                if (null != callStoredProcedure)
                {
                    LOG.info("calling cleanUp");
                    callStoredProcedure.cleanup();
                }
            }
        }
        return true;
    }

    @Override
    public boolean createSimpleQuestion(Question question) {
        LOG = SystemConfig.instance().getLOG();
        LOG.info("In createSimpleQuestion method");

        CallStoredProcedure callStoredProcedure = null;
        try{
            LOG.info("Calling Stored Procedure");
            callStoredProcedure = new CallStoredProcedure("spCreateSimpleQuestion(?,?,?,?)");
            callStoredProcedure.setParameter(1, question.getQuestionTitle());
            callStoredProcedure.setParameter(2, question.getQuestionText());
            callStoredProcedure.setParameter(3, question.getQuestionType());
            callStoredProcedure.setParameter(4, question.getBannerID());
            callStoredProcedure.execute();
        }catch (SQLException e){
            LOG.error("sql query exception", e);
            e.printStackTrace();
            return false;
        }finally {
            if (null != callStoredProcedure)
            {
                LOG.info("calling cleanUp");
                callStoredProcedure.cleanup();
            }
        }
        return true;
    }

    @Override
    public List<Question> loadQuestionByInstID(long id)
    {
        LOG = SystemConfig.instance().getLOG();
        LOG.info("In loadQuestionByInstID method");

        List<Question> questions = new ArrayList<Question>();
        CallStoredProcedure proc = null;
        try
        {
            LOG.info("Performing query");
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
        } catch (SQLException e) {
            LOG.error("sql query exception", e);
        } finally {
            if (null != proc) {
                LOG.info("calling cleanUp");
                proc.cleanup();
            }
        }
        return questions;
    }

    @Override
    public List<Question> loadQuestionByQID(long id)
    {
        LOG = SystemConfig.instance().getLOG();
        LOG.info("In loadQuestionByQID method");

        List<Question> questions = new ArrayList<Question>();
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("sploadQuestionByQuestionID(?)");
            proc.setParameter(1, id);
            LOG.info("Performing query");
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
                LOG.debug("Results fetched" + results.getRow());
            }
        }
        catch (SQLException e)
        {
            LOG.error("sql query exception", e);
        }
        finally
        {
            if (null != proc)
            {
                LOG.info("calling cleanUp");
                proc.cleanup();
            }
        }
        return questions;
    }

    @Override
    public boolean deleteQuestionById(long id) {
        LOG = SystemConfig.instance().getLOG();
        LOG.info("In deleteQuestionById method");
        CallStoredProcedure callStoredProcedure = null;
        try{
            LOG.info("Performing 4 queries");
            callStoredProcedure = new CallStoredProcedure("spDeleteQuestionById(?)");
            callStoredProcedure.setParameter(1, id);

            callStoredProcedure.execute();
        }catch (SQLException e){
            LOG.error("sql query exception", e);
            e.printStackTrace();
            return false;
        }finally {
            if (null != callStoredProcedure)
            {
                LOG.info("calling cleanUp");
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

    //Load all question ID's linked to a survey
    public List<Long> loadQuestionIDbySurveyID(long SurveyID) {

        List<Long> questions = new ArrayList<Long>();
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spLoadQuestionsbySurveyID(?)");
            proc.setParameter(1, SurveyID);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    long qid =results.getLong("questionID");
                    questions.add(qid);

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

    public Question loadQuestionbyID(long id)
    {
        Question q=new Question();
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spLoadQuestionbyID(?)");
            proc.setParameter(1, id);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                long qid =results.getLong("questionID");
                String title = results.getString("questionTitle");
                String text = results.getString("questionText");
                Date date=results.getDate("creationDate");
                q.setQuestionID(qid);
                q.setQuestionTitle(title);
                q.setQuestionText(text);
                q.setCreationDate(date);
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
        return q;
    }
}
