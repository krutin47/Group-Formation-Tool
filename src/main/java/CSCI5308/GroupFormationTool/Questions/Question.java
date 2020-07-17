package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.SystemConfig;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;

public class Question implements Comparable<Question>{
    private long questionID;
    private String questionTitle;
    private String questionText;
    private Date creationDate;
    private long typeID;
    private String questionType;
    private String bannerID;
    List<Choice> choices;

    public Question(){
        //default constructor
    }

    public Question(String questionTitle, String questionText, String questionType, String bannerID, List<Choice> choices) {

        SystemConfig.instance().getLOG().info("In Constructor with parameters");
        SystemConfig.instance().getLOG().debug("Checking Parameter questionTitle :: " + questionTitle.isEmpty());
        SystemConfig.instance().getLOG().debug("Checking Parameter questionText :: " + questionText.isEmpty());
        SystemConfig.instance().getLOG().debug("Checking Parameter questionType :: " + questionType.isEmpty());
        SystemConfig.instance().getLOG().debug("Checking Parameter bannerID :: " + bannerID.isEmpty());
        SystemConfig.instance().getLOG().debug("Checking Parameter choices :: " + choices.isEmpty());

        this.questionTitle = questionTitle;
        this.questionText = questionText;
        this.questionType = questionType;
        this.bannerID = bannerID;
        this.choices = choices;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public long getTypeID() {
        return typeID;
    }

    public void setTypeID(long typeID) {
        this.typeID = typeID;
    }

    public Comparator<Question> getSortTitle() {
        return sortTitle;
    }

    public void setSortTitle(Comparator<Question> sortTitle) {
        this.sortTitle = sortTitle;
    }

    public String getBannerID() {
        return bannerID;
    }

    public void setBannerID(String bannerID) {
        this.bannerID = bannerID;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    //TO DELETE AN EXISTING QUESTION based on questionID
    public boolean deleteQuestion(IQuestion questionDB)
    {
        return questionDB.deleteQuestionById(questionID);
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