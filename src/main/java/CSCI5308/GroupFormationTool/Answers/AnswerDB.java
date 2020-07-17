package CSCI5308.GroupFormationTool.Answers;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDB implements IAnswer {

    public boolean createAnswer(Answer answer,long surveyID,long QuestionID,long userID) {
        CallStoredProcedure callStoredProcedure = null;
        try{
            callStoredProcedure = new CallStoredProcedure("spCreateAnswer(?,?,?,?)");
            callStoredProcedure.setParameter(1, answer.getAnswerValue());
            callStoredProcedure.setParameter(2,surveyID);
            callStoredProcedure.setParameter(3,QuestionID);
            callStoredProcedure.setParameter(4,userID);
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

    public String loadAnswerbyID(long ID)
    {
        String s=new String();
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spLoadAnswerValue(?)");
            proc.setParameter(1, ID);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    s=results.getString("answerValue");
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
        return s;
    }

    public long getSurveyIDByCourseID(long courseID){
        long surveyID = 0;
        CallStoredProcedure proc = null;

        try {
            proc = new CallStoredProcedure("spLoadSurveyIDByQuestionID(?)");
            proc.setParameter(1, courseID);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                surveyID = results.getLong("surveyID");
            }
        }
        catch (SQLException e) {
            // Logging needed.
        }
        finally {
            if (null != proc)
            {
                proc.cleanup();
            }
        }

        return surveyID;
    }

    public List<Long> loadListUserAnswered(long surveyID){
        List<Long> usersID = new ArrayList<>();
        CallStoredProcedure procedure = null;
        try {
            procedure = new CallStoredProcedure("spLoadStudentsAnswered(?)");
            procedure.setParameter(1, surveyID);
            ResultSet resultSet = procedure.executeWithResults();
            if (null != resultSet){
                while (resultSet.next()){
                    usersID.add(resultSet.getLong("id"));
                }
            }
        } catch (SQLException e) {

        } finally {
            if (null != procedure){
                procedure.cleanup();
            }
        }
        return usersID;
    }

    public List<Answer> loadAnswersByUserID(long userID){
        List<Answer> answers = new ArrayList<>();
        CallStoredProcedure procedure = null;
        try {
            procedure = new CallStoredProcedure("spLoadAnswersByUserID(?)");
            procedure.setParameter(1, userID);
            ResultSet resultSet = procedure.executeWithResults();
            if (null != resultSet){
                while (resultSet.next()){
                    answers.add(new Answer(resultSet.getString("answerValue")));
                }
            }
        } catch (SQLException e) {

        } finally {
            if (null != procedure){
                procedure.cleanup();
            }
        }
        return answers;
    }
}



