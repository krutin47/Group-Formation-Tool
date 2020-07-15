package CSCI5308.GroupFormationTool.AnswersTest;

import CSCI5308.GroupFormationTool.Answers.Answer;
import CSCI5308.GroupFormationTool.Questions.Question;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AnswerTest {

    @Test
    public void ConstructorTest()
    {
        Answer answer=new Answer();
        assertNull(answer.getAnswerValue());
        assertTrue(answer.getAnswerID()==0);

    }

    @Test
    public void setAnswerIdTest() {
        Answer answer=new Answer();
        answer.setAnswerID(0);
        assertTrue(answer.getAnswerID()==0);
    }

    @Test
    public void getAnswerIdTest() {
        Answer answer=new Answer();
        answer.setAnswerID(0);
        assertTrue(answer.getAnswerID()==0);
    }

    @Test
    public void setAnswerValueTest() {
        Answer answer=new Answer();
        answer.setAnswerValue("temp");
        assertTrue(answer.getAnswerValue().equals("temp"));
    }

    @Test
    public void getAnswerValueTest() {
        Answer answer=new Answer();
        answer.setAnswerValue("try");
        assertTrue(answer.getAnswerValue().equals("try"));
    }

}


