package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Database.CallQuery;
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
