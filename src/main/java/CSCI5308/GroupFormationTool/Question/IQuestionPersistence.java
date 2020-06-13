package CSCI5308.GroupFormationTool.Question;



import java.util.List;

public interface IQuestionPersistence {

    //loadallquestions
    public List<Question> loadAllQuestion();

    public void loadQuestionByID(long id, Question question);

    public boolean createQuestion(Question question);

    public boolean deleteQuestion(long id);
}
