package CSCI5308.GroupFormationTool.Question;

import java.sql.Date;
import java.util.Comparator;

import org.springframework.boot.autoconfigure.domain.EntityScan;


public class Question implements Comparable<Question>
{
    private long questionID;
    private String questionTitle;
    private String questionText;
    private long typeID;
	private Date creationDate;

    public long getTypeID() {
		return typeID;
	}

	public void setTypeID(long typeID) {
		this.typeID = typeID;
	}

	public long getQuestionID() {
		return questionID;
	}

	public void setQuestionID(long questionID) {
		this.questionID = questionID;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
   

	//default constructor
    public Question()
    {
        setDefaults();
    }

   //find question by id
    public Question(long id,IQuestionPersistence questionDB)
    {
        setDefaults();
        //questionDB.loadQuestionByID(id);
        questionDB.loadQuestionByInstID(id);

    }

   //set default values
    public void setDefaults()
    {
        questionID = -1;
        questionTitle = "";
        questionText="";
        typeID=-1;
        creationDate= null;
    }



    //TO DELETE AN EXISTING QUESTION based on questionID
    public boolean deleteQuestion(IQuestionPersistence questionDB)
    {
       // return questionDB.deleteQuestion(questionID);
    	return questionDB.deleteQuestionById(questionID);
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

   
    public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public Comparator<Question> sortTitle = new Comparator<Question>() {
		@Override
		public int compare(Question m1, Question m2) {
	        return m1.questionTitle.compareTo(m2.questionTitle);
	    }
	};

	@Override
	  public int compareTo(Question u) {
		 if (creationDate == null || u.creationDate == null) {
		      return 0;
		    }
	    return creationDate.compareTo(u.creationDate);
	  }

}
