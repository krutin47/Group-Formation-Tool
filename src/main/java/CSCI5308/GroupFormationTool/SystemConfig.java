package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.Answers.AnswerDB;
import CSCI5308.GroupFormationTool.Answers.IAnswer;
import CSCI5308.GroupFormationTool.Answers.IStudentSurvey;
import CSCI5308.GroupFormationTool.Answers.StudentSurveyDB;
import CSCI5308.GroupFormationTool.Questions.*;
import CSCI5308.GroupFormationTool.Security.*;
import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.Database.*;
import CSCI5308.GroupFormationTool.Courses.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/*
 * This is a singleton, we will learn about these when we learn design patterns.
 *
 * The single responsibility of this singleton is to store concrete classes
 * selected by the system for use in the rest of the system. This will allow
 * a form of dependency injection in places where we cannot use normal
 * dependency injection (for example classes that override or extend existing
 * library classes in the framework).
 */
public class SystemConfig
{
	private static SystemConfig uniqueInstance = null;
	private IPasswordEncryption passwordEncryption;
	private IUserPersistence userDB;
	private IDatabaseConfiguration databaseConfiguration;
	private ICoursePersistence courseDB;
	private ICourseUserRelationshipPersistence courseUserRelationshipDB;
	private IQuestion questionService;
	private IQuestionType questionTypeService;
	private Properties properties;
	private IPasswordPolicies passwordPolicies;
	private IAnswer answerDB;
	private IStudentSurvey studentSurvey;
	private IChoice choice;



	// This private constructor ensures that no class other than System can allocate
	// the System object. The compiler would prevent it.
	private SystemConfig() {
		// The default instantiations are the choices that would be used in the
		// production application. These choices can all be overridden by test
		// setup logic when necessary.
		passwordEncryption = new BCryptPasswordEncryption();
		userDB = new UserDB();
		databaseConfiguration = new DefaultDatabaseConfiguration();
		courseDB = new CourseDB();
		courseUserRelationshipDB = new CourseUserRelationshipDB();
		questionService = new QuestionService();
		questionTypeService = new QuestionTypeService();
		passwordPolicies = new DefaultPasswordPolicies();
		properties = new Properties();
		answerDB=new AnswerDB();
		studentSurvey=new StudentSurveyDB();
		choice=new ChoiceService();
		String propertyFilePath = "src/main/resources/application.properties";
		try(FileInputStream in = new FileInputStream(propertyFilePath)) {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public IPasswordPolicies getPasswordPolicies() {
		return passwordPolicies;
	}

	public void setPasswordPolicies(IPasswordPolicies passwordPolicies) {
		this.passwordPolicies = passwordPolicies;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}


	// This is the way the rest of the application gets access to the System object.
	public static SystemConfig instance() {
		// Using lazy initialization, this is the one and only place that the System
		// object will be instantiated.
		if (null == uniqueInstance)
		{
			uniqueInstance = new SystemConfig();
		}
		return uniqueInstance;
	}

	public IPasswordEncryption getPasswordEncryption()
	{
		return passwordEncryption;
	}

	public void setPasswordEncryption(IPasswordEncryption passwordEncryption) {
		this.passwordEncryption = passwordEncryption;
	}


	public IUserPersistence getUserDB() {

		return userDB;
	}

	public void setUserDB(IUserPersistence userDB)

	{
		this.userDB = userDB;
	}

	public IDatabaseConfiguration getDatabaseConfiguration()
	{
		return databaseConfiguration;
	}

	public void setDatabaseConfiguration(IDatabaseConfiguration databaseConfiguration) {
		this.databaseConfiguration = databaseConfiguration;
	}


	public void setCourseDB(ICoursePersistence courseDB) {
		this.courseDB = courseDB;
	}


	public ICoursePersistence getCourseDB() {
		return courseDB;
	}


	public void setCourseUserRelationshipDB(ICourseUserRelationshipPersistence courseUserRelationshipDB) {
		this.courseUserRelationshipDB = courseUserRelationshipDB;
	}

	public ICourseUserRelationshipPersistence getCourseUserRelationshipDB()
	{
		return courseUserRelationshipDB;
	}

	public IQuestion getQuestionService() {
		return questionService;
	}

	public void setQuestionService(IQuestion questionService) {
		this.questionService = questionService;
	}

	public IQuestionType getQuestionTypeService() {
		return questionTypeService;
	}

	public void setQuestionTypeService(IQuestionType questionTypeService) {
		this.questionTypeService = questionTypeService;
	}

	public IAnswer getAnswerDB()
	{
		return answerDB;
	}

	public void setAnswerDB(IAnswer answerDB)
	{
		this.answerDB=answerDB;
	}

	public IStudentSurvey getStudentSurvey()
	{
		return this.studentSurvey;
	}

	public void setStudentSurvey(IStudentSurvey studentSurvey) { this.studentSurvey=studentSurvey; }

	public IChoice getChoice()
	{
		return this.choice;
	}

	public void setChoice(IChoice choice) { this.choice=choice; }

	}


