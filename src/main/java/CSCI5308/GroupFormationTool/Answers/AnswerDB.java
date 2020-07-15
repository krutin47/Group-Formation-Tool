package CSCI5308.GroupFormationTool.Answers;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerDB implements IAnswer {



   //Create new Answer in Database
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




    //Load answer by answerID
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


}




