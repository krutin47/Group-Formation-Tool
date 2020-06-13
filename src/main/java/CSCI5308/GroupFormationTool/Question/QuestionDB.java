package CSCI5308.GroupFormationTool.Question;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDB implements IQuestionPersistence{


    //Load all questions in DB
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
                    q.setId(id);
                    q.setTitle(title);
                    q.setText(text);
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
            proc = new CallStoredProcedure("spFindCourseByID(?)");
            proc.setParameter(1, id);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    String title = results.getString(2);   //get the question title
                    String text = results.getString(3);   //get the text
                    question.setId(id);   //Set ID
                    question.setTitle(title);   //Set Title
                    question.setText(text);    //Set Text
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




    //To create a question in DB
    public boolean createQuestion(Question question)
    {
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spCreateQuestion(?, ?,?)");
            proc.setParameter(1, question.getTitle());
            proc.setParameter(2,question.getText());
            proc.registerOutputParameterLong(3);
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

