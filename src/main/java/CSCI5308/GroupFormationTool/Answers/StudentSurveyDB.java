package CSCI5308.GroupFormationTool.Answers;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Questions.Question;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentSurveyDB implements IStudentSurvey{


    public long getSurveyIDfromCourse(long id)
    {
        CallStoredProcedure proc = null;
        long sid=0;
        try
        {
            proc = new CallStoredProcedure("spLoadSurveyIDbyCourseID(?)");
            proc.setParameter(1, id);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while(results.next())
                {
                    sid=results.getLong("surveyID");
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
        return sid;
    }



}
