package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionTypeService implements IQuestionType{
    @Override
    public List<QuestionType> loadAllQuestionTypes() {

        SystemConfig.instance().getLOG().info("In loadAllQuestionTypes method");

        List<QuestionType> questionTypeList = new ArrayList<>();
        CallStoredProcedure storedProcedure = null;

        try{
            SystemConfig.instance().getLOG().info("Calling Stored Procedure");

            storedProcedure = new CallStoredProcedure("spLoadAllQuestionTypes()");
            ResultSet resultSet = storedProcedure.executeWithResults();

            if(null != resultSet){
                while (resultSet.next()){
                    long id = resultSet.getLong(1);
                    String type = resultSet.getString(2);
                    QuestionType questionType = new QuestionType(id, type);
                    questionTypeList.add(questionType);
                }
                SystemConfig.instance().getLOG().debug("Fetched ResultSet records :: " + resultSet.getRow());
            }

        } catch (SQLException e) {
            SystemConfig.instance().getLOG().error("SQL query error", e);
            e.printStackTrace();
        } finally {
            if (null != storedProcedure){
                SystemConfig.instance().getLOG().info("cleaningUp resources");
                storedProcedure.cleanup();
            }
        }
        return questionTypeList;
    }
}
