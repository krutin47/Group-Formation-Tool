package CSCI5308.GroupFormationTool.AnswersTest;

import CSCI5308.GroupFormationTool.Answers.Answer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AnswerTest {

    @Test
    public void AnswerConstructorTest(){
        Answer answer = new Answer();
        assertTrue(answer.getQuestionID()==0);
        assertTrue(answer.getAnswerID() == 0);
        assertNull(answer.getAnswerValue());
    }

}
