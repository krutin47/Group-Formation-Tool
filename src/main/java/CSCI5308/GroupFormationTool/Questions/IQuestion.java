package CSCI5308.GroupFormationTool.Questions;

import java.util.List;

public interface IQuestion {

    public boolean createMCQuestion(Question question);

    public boolean createSimpleQuestion(Question question);

    public List<Question> loadQuestionByInstID(long id);

    public List<Question> loadQuestionByQID(long id);

    public Question loadQuestionbyID(long id);

    public boolean deleteQuestionById(long id);

    public Question loadQuestionbyQid(long id);

    public List<Question> loadInstructorQuestionsList(long courseID);


    public List<Long> loadQuestionIDbySurveyID(long SurveyID);
}