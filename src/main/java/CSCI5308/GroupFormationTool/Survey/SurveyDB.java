package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SurveyDB implements ISurveyDB {
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
