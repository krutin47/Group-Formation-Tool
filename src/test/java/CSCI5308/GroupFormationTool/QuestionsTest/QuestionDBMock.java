package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Questions.Choice;
import CSCI5308.GroupFormationTool.Questions.IQuestion;
import CSCI5308.GroupFormationTool.Questions.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionDBMock implements IQuestion {

    List<Question> questions = new ArrayList<Question>();

    @Override
    public boolean createMCQuestion(Question question) {
        questions.add(question);
        return true;
    }

    @Override
    public boolean createSimpleQuestion(Question question) {
        questions.add(question);
        return true;
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


    public Question loadQuestionbyID(long id) {
        Question question=new Question();
        question.setQuestionID(0);
        question.setQuestionTitle("Temp");
        question.setQuestionText("Test");
        question.setTypeID(1);
        return question;
    }

    public boolean deleteQuestionById(long id) {
        Question question=new Question();
        question.setQuestionID(0);
        question.setQuestionTitle("Temp");
        question.setQuestionText("Test");
        question.setTypeID(1);
        question = new Question();
        return true;
    }


    public List<Long> loadQuestionIDbySurveyID(long SurveyID) {
        List<Long> questionList = new ArrayList<>();
        Question question = new Question();
        question.setQuestionID(0);
        question.setQuestionTitle("Temp");
        question.setQuestionText("Test");
        question.setTypeID(1);
        questionList.add(question.getQuestionID());
        question=new Question();
        question.setQuestionID(1);
        question.setQuestionTitle("Temp2");
        question.setQuestionText("Test2");
        question.setTypeID(2);
        questionList.add(question.getQuestionID());
        return questionList;
    }
     @Override
    public Question loadQuestionbyQid(long id)
    {
        Question question = new Question();
        question.setQuestionID(0);
        question.setQuestionTitle("Temp");
        question.setQuestionText("Test");
        question.setTypeID(1);
        question=new Question();
        question.setQuestionID(1);
        question.setQuestionTitle("Temp2");
        question.setQuestionText("Test2");
        question.setTypeID(2);
        return question;
    }
    public List<Question> loadInstructorQuestionsList(long id) {
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
}
