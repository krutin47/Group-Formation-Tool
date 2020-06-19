package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionTypeService implements IQuestionType{
    @Override
    public List<QuestionType> loadAllQuestionTypes() {

        List<QuestionType> questionTypeList = new ArrayList<QuestionType>();

        CallStoredProcedure storedProcedure = null;
        try{
            storedProcedure = new CallStoredProcedure("spLoadAllQuestionTypes()");
            ResultSet resultSet = storedProcedure.executeWithResults();

            if(null != resultSet){
                while (resultSet.next()){
                    long id = resultSet.getLong(1);
                    String type = resultSet.getString(2);
                    QuestionType questionType = new QuestionType(id, type);
                    questionTypeList.add(questionType);
                }
            }

        } catch (SQLException e) {
            //e.printStackTrace();
        } finally {
            if (null != storedProcedure){
                storedProcedure.cleanup();
            }
        }
        return questionTypeList;
    }
}
