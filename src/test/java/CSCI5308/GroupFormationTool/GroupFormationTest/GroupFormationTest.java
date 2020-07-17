package CSCI5308.GroupFormationTool.GroupFormationTest;

import CSCI5308.GroupFormationTool.Answers.Answer;
import CSCI5308.GroupFormationTool.Answers.AnswerStudentMapper;
import CSCI5308.GroupFormationTool.GroupFormationManagement.Group;
import CSCI5308.GroupFormationTool.GroupFormationManagement.IGroupFormation;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class GroupFormationTest {
    IGroupFormation groupFormation = SystemConfig.instance().getGroupFormation();

    @Test
    public void createGroup(){
        List<AnswerStudentMapper> studentMappers = new ArrayList<>();
        List<Answer> answers1 = new ArrayList<>(Arrays.asList(new Answer("Yes"), new Answer("Java"), new Answer("6"), new Answer("Ahmedabad")));
        studentMappers.add(new AnswerStudentMapper(1, answers1));
        List<Answer> answers8 = new ArrayList<>(Arrays.asList(new Answer("No"), new Answer("Python"), new Answer("2"), new Answer("Ahmedabad")));
        studentMappers.add(new AnswerStudentMapper(8, answers8));
        List<Answer> answers11 = new ArrayList<>(Arrays.asList(new Answer("No"), new Answer("C"), new Answer("3"), new Answer("Baroda")));
        studentMappers.add(new AnswerStudentMapper(11, answers11));
        List<Answer> answers13 = new ArrayList<>(Arrays.asList(new Answer("Yes"), new Answer("Python"), new Answer("7"), new Answer("Mumbai")));
        studentMappers.add(new AnswerStudentMapper(13, answers13));
        List<Answer> answers14 = new ArrayList<>(Arrays.asList(new Answer("Yes"), new Answer("C++"), new Answer("5"), new Answer("Mahesana")));
        studentMappers.add(new AnswerStudentMapper(14, answers14));

        List<Group> groups = new ArrayList<>();

        groups = groupFormation.createGroup(studentMappers, 2);
        assertTrue(groups.size() != 0);
    }

    @Test
    public void createRandomGroup(){
        List<AnswerStudentMapper> studentMappers = new ArrayList<>();
        List<Answer> answers1 = new ArrayList<>(Arrays.asList(new Answer("Yes"), new Answer("Java"), new Answer("6"), new Answer("Ahmedabad")));
        studentMappers.add(new AnswerStudentMapper(1, answers1));
        List<Answer> answers8 = new ArrayList<>(Arrays.asList(new Answer("No"), new Answer("Python"), new Answer("2"), new Answer("Ahmedabad")));
        studentMappers.add(new AnswerStudentMapper(8, answers8));
        List<Answer> answers11 = new ArrayList<>(Arrays.asList(new Answer("No"), new Answer("C"), new Answer("3"), new Answer("Baroda")));
        studentMappers.add(new AnswerStudentMapper(11, answers11));
        List<Answer> answers13 = new ArrayList<>(Arrays.asList(new Answer("Yes"), new Answer("Python"), new Answer("7"), new Answer("Mumbai")));
        studentMappers.add(new AnswerStudentMapper(13, answers13));
        List<Answer> answers14 = new ArrayList<>(Arrays.asList(new Answer("Yes"), new Answer("C++"), new Answer("5"), new Answer("Mahesana")));
        studentMappers.add(new AnswerStudentMapper(14, answers14));

        List<Group> groups = new ArrayList<>();

        groups = groupFormation.createRandomGroup(studentMappers, 2);
        assertTrue(groups.size() == 3);
    }
}
