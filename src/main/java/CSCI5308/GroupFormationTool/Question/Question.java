package CSCI5308.GroupFormationTool.Question;


import org.springframework.web.bind.annotation.ModelAttribute;

import static CSCI5308.GroupFormationTool.AccessControl.User.isStringNullOrEmpty;

public class Question
{
    private long questionID;
    private String questionTitle;
    private String questionText;
    private long typeID;

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
        questionID = -1;
        questionTitle = "";
        questionText="";
        typeID=-1;
    }



    //TO DELETE AN EXISTING QUESTION based on questionID
    public boolean delete(IQuestionPersistence questionDB)
    {
        return questionDB.deleteQuestion(questionID);
    }



    //TO CREATE A NEW QUESTION
    public boolean createQuestion(IQuestionPersistence questionDB)
    {
        return questionDB.createQuestion(this);
    }


    //Validations

    public static boolean isTitlevalid(String title)
    {
        if (null == title)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean isTextvalid(String text)
    {
        if (null == text)
        {
            return true;
        }
        else
        {
            return false;
        }
    }



    //Getters and Setters
    public void setId(long id)
    {
        this.questionID = id;
    }

    public long getId()
    {
        return questionID;
    }

    public void setTitle(String title)
    {
        this.questionTitle = title;
    }

    public String getTitle()
    {
        return questionTitle;
    }

    public void setText(String text)
    {
        this.questionText = text;
    }

    public String getText()
    {
        return questionText;
    }

    public long getTypeID()
    {
       return typeID;
    }

    public void setTypeID(long id)
    {
        this.typeID=id;
    }


}
