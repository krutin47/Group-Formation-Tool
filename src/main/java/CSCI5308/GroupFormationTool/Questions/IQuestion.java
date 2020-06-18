package CSCI5308.GroupFormationTool.Questions;

import java.util.List;

public interface IQuestion {

    public boolean createMCQuestion(Question question);

    public boolean createSimpleQuestion(Question question);

    public List<Question> loadQuestionByInstID(long id);

    public List<Question> loadQuestionByQID(long id);

    public boolean deleteQuestionById(long id);
}