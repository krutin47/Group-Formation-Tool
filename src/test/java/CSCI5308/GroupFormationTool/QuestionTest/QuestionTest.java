package CSCI5308.GroupFormationTool.QuestionTest;
import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.Question.QuestionDB;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.springframework.util.Assert.isTrue;


@SpringBootTest
@SuppressWarnings("deprecation")
class QuestionTest
{
    @Test
    public void ConstructorTests()
    {
        Question question=new Question();
        Assert.isTrue(question.getId() == -1);
        Assert.isTrue(question.getTitle().isEmpty());
        Assert.isTrue(question.getText().isEmpty());
        Assert.isTrue(question.getTypeID()== -1);

        IQuestionPersistence questionDB=new QuestionDBMock();
        question=new Question(0,questionDB);
        Assert.isTrue(question.getId() == 0);
        Assert.isTrue(question.getTitle().equals("Temp"));
        Assert.isTrue(question.getText().equals("Test"));
        Assert.isTrue(question.getTypeID() == 1);
    }

    @Test
    public void setIdTest()
    {
        Question question=new Question();
        question.setId(7);
        Assert.isTrue(question.getId() == 7);
    }

    @Test
    public void getIdTest()
    {
        Question question=new Question();
        question.setId(7);
        Assert.isTrue(question.getId() == 7);
    }

    @Test
    public void setTitleTest()
    {
        Question question=new Question();
        question.setTitle("Temp");
        Assert.isTrue(question.getTitle().equals("Temp"));

    }

    @Test
    public void getTitleTest()
    {
        Question question=new Question();
        question.setTitle("Temp");
        Assert.isTrue(question.getTitle().equals("Temp"));
    }

    @Test
    public void setTextTest()
    {
        Question question=new Question();
        question.setText("Test");
        Assert.isTrue(question.getText().equals("Test"));
    }


    @Test
    public void getTextTest()
    {
        Question question=new Question();
        question.setTitle("Test");
        Assert.isTrue(question.getTitle().equals("Test"));
    }

    @Test
    public void getTypeIDTest()
    {
        Question question=new Question();
        question.setTypeID(0);
        Assert.isTrue(question.getTypeID() == 0);
    }

    @Test
    public void setTypeIDTest()
    {
        Question question=new Question();
        question.setTypeID(0);
        Assert.isTrue(question.getTypeID() == 0);
    }

    @Test
    public void deleteQuestionTest()
    {
        IQuestionPersistence questionDB=new QuestionDBMock();
        boolean status=questionDB.deleteQuestion(0);
        Assert.isTrue(status);

    }

    @Test
    public void createQuestionTest()
    {
        IQuestionPersistence questionDB=new QuestionDBMock();
        Question question=new Question();
        question.setId(0);
        question.setTitle("Temp");
        question.setText("Test");
        question.setTypeID(1);
        questionDB.createQuestion(question);
        Assert.isTrue(question.getId()==0);
        Assert.isTrue(question.getTitle().equals("Temp"));
        Assert.isTrue(question.getText().equals("Test"));
        Assert.isTrue(question.getTypeID()==1);

    }

}

