package CSCI5308.GroupFormationTool.AnswersTest;

import CSCI5308.GroupFormationTool.Answers.Answer;
import CSCI5308.GroupFormationTool.Answers.IAnswer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AnswerDBTest implements IAnswer {


    public boolean createAnswer(Answer answer, long surveyID, long QuestionID, long userID) {
        Answer ans=new Answer();
        ans.setAnswerValue("temp");
        ans.setAnswerID(0);
        return true;
    }


    public String loadAnswerbyID(long ID) {
        Answer ans=new Answer();
        ans.setAnswerID(ID);
        return ans.getAnswerValue();
    }
}
