package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.AccessControl.IUserNotifications;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.UserDB;
import CSCI5308.GroupFormationTool.AccessControl.UserNotifications;
import CSCI5308.GroupFormationTool.Answers.AnswerDB;
import CSCI5308.GroupFormationTool.Answers.IAnswer;
import CSCI5308.GroupFormationTool.Answers.IStudentSurvey;
import CSCI5308.GroupFormationTool.Answers.StudentSurveyDB;
import CSCI5308.GroupFormationTool.Courses.CourseDB;
import CSCI5308.GroupFormationTool.Courses.CourseUserRelationshipDB;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Database.DefaultDatabaseConfiguration;
import CSCI5308.GroupFormationTool.Database.IDatabaseConfiguration;
import CSCI5308.GroupFormationTool.Questions.*;
import CSCI5308.GroupFormationTool.Security.BCryptPasswordEncryption;
import CSCI5308.GroupFormationTool.Security.DefaultPasswordPolicies;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.Security.IPasswordPolicies;
import CSCI5308.GroupFormationTool.Utils.IEmail;
import CSCI5308.GroupFormationTool.Utils.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private IUserNotifications userNotifications;
	private IDatabaseConfiguration databaseConfiguration;
	private ICoursePersistence courseDB;
	private ICourseUserRelationshipPersistence courseUserRelationshipDB;
	private IQuestion questionService;
	private IQuestionType questionTypeService;
	private Properties properties;
	private IPasswordPolicies passwordPolicies;
	private IEmail mailUtil;
	private Logger LOG;

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
		userNotifications = new UserNotifications();
		databaseConfiguration = new DefaultDatabaseConfiguration();
		courseDB = new CourseDB();
		courseUserRelationshipDB = new CourseUserRelationshipDB();
		questionService = new QuestionService();
		questionTypeService = new QuestionTypeService();
		passwordPolicies = new DefaultPasswordPolicies();

		LOG = LoggerFactory.getLogger(GroupFormationToolApplication.class);

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

		mailUtil = new MailUtil();
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

	public IEmail getMailUtil() {
		return mailUtil;
	}

	public void setMailUtil(IEmail mailUtil) {
		this.mailUtil = mailUtil;
	}

	public IUserNotifications getUserNotifications() {
		return userNotifications;
	}

	public void setUserNotifications(IUserNotifications userNotifications) {
		this.userNotifications = userNotifications;
	}

	public Logger getLOG() {
		return LOG;
	}

	public void setLOG(Logger LOG) {
		this.LOG = LOG;
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
}
