package CSCI5308.GroupFormationTool.QuestionTest;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Question.Question;
import java.util.ArrayList;
import java.util.List;

public class QuestionDBMock implements IQuestionPersistence {

    public List<Question> loadAllQuestion() {

        List<Question> questionList = new ArrayList<>();
        Question question = new Question();
        question.setQuestionID(0);
        question.setQuestionTitle("Temp");
        question.setQuestionText("Test");
        question.setTypeID(1);
        questionList.add(question);
        question=new Question();
        question.setQuestionID(1);
        question.setQuestionTitle("Temp2");
        question.setQuestionText("Test2");
        question.setTypeID(2);
        questionList.add(question);
        return questionList;
    }


    public void loadQuestionByID(long id, Question question) {
        question.setQuestionID(id);
        question.setQuestionTitle("Temp");
        question.setQuestionText("Test");
        question.setTypeID(1);
    }


    public boolean createQuestion(Question question) {
        question.setQuestionID(0);
        question.setQuestionTitle("Temp");
        question.setQuestionText("Test");
        question.setTypeID(1);
        return true;
    }


    public boolean deleteQuestion(long id)
    {
        Question question=new Question();
        question.setQuestionID(0);
        question.setQuestionTitle("Temp");
        question.setQuestionText("Test");
        question.setTypeID(1);
        question.setDefaults();
        return true;
    }


    public List<Question> loadAllQuestionfromDB() {
        List<Question> questionList = new ArrayList<>();
        Question question = new Question();
        question.setQuestionID(0);
        question.setQuestionTitle("Temp");
        question.setQuestionText("Test");
        question.setTypeID(1);
        questionList.add(question);
        question=new Question();
        question.setQuestionID(1);
        question.setQuestionTitle("Temp2");
        question.setQuestionText("Test2");
        question.setTypeID(2);
        questionList.add(question);
        return questionList;
    }



    public List<Question> loadQuestionByInstID(long id) {
        List<Question> questionList = new ArrayList<>();
        Question question = new Question();
        User user=new User();
        user.setID(22);
        question.setQuestionID(0);
        question.setQuestionTitle("Temp");
        question.setQuestionText("Test");
        question.setTypeID(1);
        questionList.add(question);
        question=new Question();
        question.setQuestionID(1);
        question.setQuestionTitle("Temp2");
        question.setQuestionText("Test2");
        question.setTypeID(2);
        questionList.add(question);
        return questionList;
    }

    public List<Question> loadQuestionByQID(long id) {
        List<Question> questionList = new ArrayList<>();
        Question question = new Question();
        question.setQuestionID(0);
        question.setQuestionTitle("Temp");
        question.setQuestionText("Test");
        question.setTypeID(1);
        questionList.add(question);
        question=new Question();
        question.setQuestionID(1);
        question.setQuestionTitle("Temp2");
        question.setQuestionText("Test2");
        question.setTypeID(2);
        questionList.add(question);
        return questionList;
    }

    public boolean deleteQuestionById(long id) {
        Question question=new Question();
        question.setQuestionID(0);
        question.setQuestionTitle("Temp");
        question.setQuestionText("Test");
        question.setTypeID(1);
        question.setDefaults();
        return true;
    }

}
