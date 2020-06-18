package CSCI5308.GroupFormationTool.QuestionTest;
import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Question.Question;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@SuppressWarnings("deprecation")
class QuestionTest
{
    @Test
    public void ConstructorTests()
    {
        Question question=new Question();
        assertTrue(question.getQuestionID() == -1);
        assertTrue(question.getQuestionTitle().isEmpty());
        assertTrue(question.getQuestionText().isEmpty());
        assertTrue(question.getTypeID()== -1);
    }

    @Test
    public void setIdTest()
    {
        Question question=new Question();
        question.setQuestionID(7);
        assertTrue(question.getQuestionID() == 7);
    }

    @Test
    public void getIdTest()
    {
        Question question=new Question();
        question.setQuestionID(7);
        assertTrue(question.getQuestionID() == 7);
    }

    @Test
    public void setTitleTest()
    {
        Question question=new Question();
        question.setQuestionTitle("Temp");
        assertTrue(question.getQuestionTitle().equals("Temp"));

    }

    @Test
    public void getTitleTest()
    {
        Question question=new Question();
        question.setQuestionTitle("Temp");
        assertTrue(question.getQuestionTitle().equals("Temp"));
    }

    @Test
    public void setTextTest()
    {
        Question question=new Question();
        question.setQuestionText("Test");
        assertTrue(question.getQuestionText().equals("Test"));
    }


    @Test
    public void getTextTest()
    {
        Question question=new Question();
        question.setQuestionTitle("Test");
        assertTrue(question.getQuestionTitle().equals("Test"));
    }

    @Test
    public void getTypeIDTest()
    {
        Question question=new Question();
        question.setTypeID(0);
        assertTrue(question.getTypeID() == 0);
    }

    @Test
    public void setTypeIDTest()
    {
        Question question=new Question();
        question.setTypeID(0);
        assertTrue(question.getTypeID() == 0);
    }

    @Test
    public void deleteQuestionTest()
    {
        IQuestionPersistence questionDB=new QuestionDBMock();
        boolean status=questionDB.deleteQuestion(0);
        assertTrue(status);

    }

    @Test
    public void createQuestionTest()
    {
        IQuestionPersistence questionDB=new QuestionDBMock();
        Question question=new Question();
        question.setQuestionID(0);
        question.setQuestionTitle("Temp");
        question.setQuestionText("Test");
        question.setTypeID(1);
        questionDB.createQuestion(question);
        assertTrue(question.getQuestionID()==0);
        assertTrue(question.getQuestionTitle().equals("Temp"));
        assertTrue(question.getQuestionText().equals("Test"));
        assertTrue(question.getTypeID()==1);

    }

}


