package CSCI5308.GroupFormationTool.Question;



public class Question
{
    private long id;
    private String title;
    private String text;
    private ITypeQuestion typedecider;

    //default constructor
    public Question()
    {
        setDefaults();
    }

   //find question by id
    public Question(long id,IQuestionPersistence questionDB)
    {
        setDefaults();
        questionDB.loadQuestionByID(id,this);

    }

   //set default values
    public void setDefaults()
    {
        id = -1;
        title = "";
        text="";
        typedecider=new QuestionType();
    }



    //TO DELETE AN EXISTING QUESTION based on questionID
    public boolean delete(IQuestionPersistence questionDB)
    {
        return questionDB.deleteQuestion(id);
    }



    //TO CREATE A NEW QUESTION
    public boolean createCourse(IQuestionPersistence questionDB)
    {
        return questionDB.createQuestion(this);
    }



    //Getters and Setters
    public void setId(long id)
    {
        this.id = id;
    }

    public long getId()
    {
        return id;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }


}
