package CSCI5308.GroupFormationTool.QuestionsTest;

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
}
