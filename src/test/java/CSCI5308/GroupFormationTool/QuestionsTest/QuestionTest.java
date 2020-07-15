package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Questions.Choice;
import CSCI5308.GroupFormationTool.Questions.IQuestion;
import CSCI5308.GroupFormationTool.Questions.Question;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QuestionTest {

    @Test
    public void ConstructorTest(){
        Question question = new Question();
        assertTrue(question.getQuestionID()==0);
        assertNull(question.getBannerID());
        assertNull(question.getCreationDate());
        assertNull(question.getQuestionText());
        assertNull(question.getQuestionTitle());
        assertNull(question.getQuestionType());
        assertNull(question.getChoices());
    }

    @Test
    public void setIdTest() {
        Question question=new Question();
        question.setQuestionID(7);
        assertTrue(question.getQuestionID() == 7);
    }

    @Test
    public void getIdTest() {
        Question question=new Question();
        question.setQuestionID(7);
        assertTrue(question.getQuestionID() == 7);
    }

    @Test
    public void setTitleTest() {
        Question question=new Question();
        question.setQuestionTitle("Temp");
        assertTrue(question.getQuestionTitle().equals("Temp"));

    }

    @Test
    public void getTitleTest() {
        Question question=new Question();
        question.setQuestionTitle("Temp");
        assertTrue(question.getQuestionTitle().equals("Temp"));
    }

    @Test
    public void setTextTest() {
        Question question=new Question();
        question.setQuestionText("Test");
        assertTrue(question.getQuestionText().equals("Test"));
    }


    @Test
    public void getTextTest() {
        Question question=new Question();
        question.setQuestionTitle("Test");
        assertTrue(question.getQuestionTitle().equals("Test"));
    }

    @Test
    public void getTypeIDTest() {
        Question question=new Question();
        question.setTypeID(0);
        assertTrue(question.getTypeID() == 0);
    }

    @Test
    public void setTypeIDTest() {
        Question question=new Question();
        question.setTypeID(0);
        assertTrue(question.getTypeID() == 0);
    }

    @Test
    public void QuestionCreationTest() {
        IQuestion iQuestion = new QuestionDBMock();
        Question question = new Question();
        question.setQuestionTitle("Normal Question Test Title");
        question.setQuestionText("Is it working?");
        question.setQuestionType("Free text");
        question.setBannerID("B00112233");
        boolean check = iQuestion.createSimpleQuestion(question);
        assertTrue(check);
    }

    @Test
    public void MCQuestionCreationTest() {
        IQuestion iQuestion = new QuestionDBMock();
        Question question = new Question();
        Choice choice = new Choice();
        choice.setOptionText("Option Test Text");
        choice.setOptionValue(1);
        question.setQuestionTitle("Normal Question Test Title");
        question.setQuestionText("Is it working?");
        question.setQuestionType("Free text");
        question.setBannerID("B00112233");
        boolean check = iQuestion.createMCQuestion(question);
        assertTrue(check);
    }

    @Test
    public void deleteQuestionByIdTest() {
        IQuestion questionDB=new QuestionDBMock();
        boolean status=questionDB.deleteQuestionById(0);
        assertTrue(status);
    }

    @Test
    public void loadQuestionByInstIDTest() {
        IQuestion questionDB=new QuestionDBMock();
        List<Question> questionList = new ArrayList<>();
        questionList=questionDB.loadQuestionByInstID(22);
        assertTrue(questionList.get(0).getQuestionID()==0);
        assertTrue(questionList.get(0).getQuestionTitle().equals("Temp"));
        assertTrue(questionList.get(0).getQuestionText().equals("Test"));
        assertTrue(questionList.get(0).getTypeID()==1);
    }

    @Test
    public void loadQuestionByQIDTest() {
        IQuestion questionDB=new QuestionDBMock();
        List<Question> questionList = new ArrayList<>();
        questionList=questionDB.loadQuestionByQID(0);
        assertTrue(questionList.get(0).getQuestionID()==0);
        assertTrue(questionList.get(0).getQuestionTitle().equals("Temp"));
        assertTrue(questionList.get(0).getQuestionText().equals("Test"));
        assertTrue(questionList.get(0).getTypeID()==1);
    }
}
