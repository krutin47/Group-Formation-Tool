package CSCI5308.GroupFormationTool.Question;



import java.util.List;

public interface IQuestionPersistence {

    //loadallquestions
    public List<Question> loadAllQuestion();

    public List<Question> loadAllQuestionfromDB();

    public void loadQuestionByID(long id, Question question);

    public List<Question> loadQuestionByInstID(long id);

    public List<Question> loadQuestionByQID(long id);

    public boolean deleteQuestionById(long id);

    public boolean createQuestion(Question question);

    public boolean deleteQuestion(long id);
}
