package CSCI5308.GroupFormationTool.QuestionTest;

import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Question.Question;

import java.util.List;

public class QuestionDBMock implements IQuestionPersistence {


    public List<Question> loadAllQuestion() {
        return null;
    }


    public void loadQuestionByID(long id, Question question) {

    }


    public boolean createQuestion(Question question) {
        return false;
    }


    public boolean deleteQuestion(long id) {
        return false;
    }
}
