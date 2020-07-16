package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChoiceService implements IChoice{

    public List<Choice> loadChoicesByQuesID(long id) {
        List<Choice> choices=new ArrayList<>();
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("sploadChoicesbyQuestionID(?)");
            proc.setParameter(1, id);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    long cid =results.getLong("optionID");
                    int value = results.getInt("optionValue");
                    String text = results.getString("optionText");
                    Choice c=new Choice();
                    c.setOptionID(cid);
                    c.setOptionText(text);
                    c.setOptionValue(value);
                    choices.add(c);
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
       return choices;
    }
}
