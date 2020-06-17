package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.SQLException;
import java.util.List;

public class QuestionService implements IQuestion {

    @Override
    public boolean createMCQuestion(Question question, List<Options> optionsList) {
        createSimpleQuestion(question);
        for (Options option: optionsList){
            CallStoredProcedure callStoredProcedure = null;
            try {
                callStoredProcedure = new CallStoredProcedure("spAddOptionForQuestion(?,?,?)");
                callStoredProcedure.setParameter(1, option.getOptionText());
                callStoredProcedure.setParameter(2, option.getOptionValue());
                callStoredProcedure.setParameter(3, question.getQuestionTitle());
                callStoredProcedure.execute();
            } catch (SQLException e) {
                return false;
            } finally {
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
            return false;
        }finally {
            if (null != callStoredProcedure)
            {
                callStoredProcedure.cleanup();
            }
        }
        return true;
    }
}
