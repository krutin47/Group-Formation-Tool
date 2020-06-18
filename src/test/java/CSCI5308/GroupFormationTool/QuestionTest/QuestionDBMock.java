package CSCI5308.GroupFormationTool.QuestionTest;
import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Question.Question;
import java.util.ArrayList;
import java.util.List;

public class QuestionDBMock implements IQuestionPersistence {

    public List<Question> loadAllQuestion() {

        List<Question> questionList = new ArrayList<>();
        Question question = new Question();
        question.setId(0);
        question.setTitle("Temp");
        question.setText("Test");
        question.setTypeID(1);
        questionList.add(question);
        question=new Question();
        question.setId(1);
        question.setTitle("Temp2");
        question.setText("Test2");
        question.setTypeID(2);
        questionList.add(question);
        return questionList;
    }


    public void loadQuestionByID(long id, Question question) {
        question.setId(id);
        question.setTitle("Temp");
        question.setText("Test");
        question.setTypeID(1);
    }


    public boolean createQuestion(Question question) {
        question.setId(0);
        question.setTitle("Temp");
        question.setText("Test");
        question.setTypeID(1);
        return true;
    }


    public boolean deleteQuestion(long id)
    {
        Question question=new Question();
        question.setId(0);
        question.setTitle("Temp");
        question.setText("Test");
        question.setTypeID(1);
        question.setDefaults();
        return true;
    }
}
