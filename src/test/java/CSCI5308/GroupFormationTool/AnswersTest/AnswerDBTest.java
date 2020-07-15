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


    @Override
    public boolean createAnswer(Answer answer, long surveyID, long QuestionID, long userID) {
        return false;
    }

    @Override
    public String loadAnswerbyID(long ID) {
        return null;
    }
}
