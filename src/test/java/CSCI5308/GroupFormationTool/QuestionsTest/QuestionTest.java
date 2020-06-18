package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Questions.Choice;
import CSCI5308.GroupFormationTool.Questions.IQuestion;
import CSCI5308.GroupFormationTool.Questions.Question;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

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
    public void QuestionCreationTest(){
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
    public void MCQuestionCreationTest(){
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
}
