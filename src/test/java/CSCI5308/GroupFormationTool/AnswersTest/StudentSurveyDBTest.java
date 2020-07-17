package CSCI5308.GroupFormationTool.AnswersTest;

import CSCI5308.GroupFormationTool.Answers.IStudentSurvey;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentSurveyDBTest implements IStudentSurvey {


    @Override
    public long getSurveyIDfromCourse(long id) {
        return 0;
    }
}
