package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.Role;
import CSCI5308.GroupFormationTool.Questions.IQuestion;
import CSCI5308.GroupFormationTool.Questions.Question;

@Controller
public class SurveyController
{
	private static final String ID = "id";
	private static final String TITLE = "title";
	private static final String FILE = "file";
	private static final String SUCCESSFUL = "successful";
	private static final String FAILURES = "failures";
	private static final String DISPLAY_RESULTS = "displayresults";
	List<Question> addition = new ArrayList<>();
	List<Long> quesIDs = new ArrayList<>();
	Long cId;

	@GetMapping("/course/survey")
	public String surveyOptions(Model model, @RequestParam(name = ID) long courseID, Long userID)
	{
		String checkIfPublished=null;
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		Course course = new Course();
		courseDB.loadCourseByID(courseID, course);
		model.addAttribute("course", course);
		cId=courseID;
		IQuestion questionDB = SystemConfig.instance().getQuestionService();
		User u=new User();
		u= CurrentUser.instance().getCurrentAuthenticatedUser();
		userID=u.getId();
		List<Question> ques=questionDB.loadInstructorQuestionsList(courseID);
		model.addAttribute("questions", ques);
		Survey survey =new Survey();
		ISurveyDB surveyDB = SystemConfig.instance().getSurveyDB();
		model.addAttribute("survey", survey);
		int checkIfExists=surveyDB.checkSurveyExists(courseID);
		long surveyID=surveyDB.loadSurveyIdbyCourseID(cId);
		checkIfPublished=surveyDB.checkSurveyIsPublished(surveyID);

		if ((course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR) ||
				course.isCurrentUserEnrolledAsRoleInCourse(Role.TA)))
		{
			if(checkIfExists==1) {
				if(checkIfPublished.contentEquals("Y")) {

					model.addAttribute("displaylinkPublish", true);
				}
				if(checkIfPublished.contentEquals("N")) {

					model.addAttribute("displaylink", true);
				}
			}
			if(checkIfExists==0) {
				model.addAttribute("displaylinkNew", true);				
			}
			return "survey/survey-options";
		}
		else
		{
			return "logout";
		}
	}


	@GetMapping("/course/survey/surveywelcomepage")
	public String createSurvey(Model model, @RequestParam(name = ID) long courseID, Long userID)
	{
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		Course course = new Course();
		courseDB.loadCourseByID(courseID, course);
		cId=courseID;
		model.addAttribute("course", course);
		IQuestion questionDB = SystemConfig.instance().getQuestionService();
		User u=new User();
		u= CurrentUser.instance().getCurrentAuthenticatedUser();
		userID=u.getId();
		List<Question> ques=questionDB.loadInstructorQuestionsList(courseID);
		model.addAttribute("questions", ques);
		Survey survey =new Survey();
		model.addAttribute("survey", survey);
		
		if ((course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR) ||
				course.isCurrentUserEnrolledAsRoleInCourse(Role.TA)))
		{
			model.addAttribute("displaylink", true);
			return "survey/surveywelcomepage";
		}
		else
		{
			return "logout";
		}
	}

	@GetMapping("/course/survey/surveywelcomepage/createsurvey")
	public String createSurveyShow(Model model, @RequestParam(name = TITLE) String title, Long courseID, Long instructorBannerID)
	{
		Course course = new Course();
		courseID=cId;
		model.addAttribute("course", course);
		IQuestion questionDB = SystemConfig.instance().getQuestionService();
		User u=new User();
		u= CurrentUser.instance().getCurrentAuthenticatedUser();
		instructorBannerID=u.getId();
		List<Question> ques=questionDB.loadInstructorQuestionsList(courseID);
		model.addAttribute("questions", ques);
		Survey survey =new Survey();
		ISurveyDB surveyDB = SystemConfig.instance().getSurveyDB();
		model.addAttribute("survey", survey);
		Boolean createResult= surveyDB.createSurvey(title, instructorBannerID, courseID);
		
		if ((createResult))
		{
			model.addAttribute("displaylink", true);
			return "survey/survey-created";
		}
		else
		{
			return "survey/survey-error";
		}

	}

	@GetMapping("/course/survey/surveywelcomepage/createsurvey/display")
	public String displaySurvey(Model model, Long userID)
	{
		IQuestion questionDB = SystemConfig.instance().getQuestionService();
		User u=new User();
		u= CurrentUser.instance().getCurrentAuthenticatedUser();
		userID=u.getId();
		List<Question> ques=questionDB.loadInstructorQuestionsList(cId);
		model.addAttribute("questions", ques);
		ISurveyDB surveyDB = SystemConfig.instance().getSurveyDB();
		long sId=surveyDB.loadSurveyIdbyCourseID(cId);
		List<Question> surveyQuestion=surveyDB.loadQuestionBySurveyID(sId);
		model.addAttribute("surveyQuestion", surveyQuestion);
		return "survey/createsurvey";
	}


	@GetMapping("/course/survey/surveywelcomepage/createsurvey/display/addques")
	public String addQuesInSurvey(Model model, @RequestParam(name = ID) long id, Long userID)
	{
		IQuestion questionDB = SystemConfig.instance().getQuestionService();
		User u=new User();
		u= CurrentUser.instance().getCurrentAuthenticatedUser();
		userID=u.getId();
		List<Question> ques=questionDB.loadInstructorQuestionsList(cId);
		model.addAttribute("questions", ques);
		Question singleQuestion =new Question();
		singleQuestion= questionDB.loadQuestionbyQid(id);
		Question checkQuestionsExists =new Question();
		checkQuestionsExists= questionDB.loadQuestionbyQid(id);
		ISurveyDB surveyDB = SystemConfig.instance().getSurveyDB();
		long sId=surveyDB.loadSurveyIdbyCourseID(cId);

		if(quesIDs.contains(id)!=true && (surveyDB.checkifQuestionInSurveyExists(id, sId)==0)) {
			addition.add(singleQuestion);
			quesIDs.add(id);
		}
		model.addAttribute("additions", addition);
		List<Question> surveyQuestion=surveyDB.loadQuestionBySurveyID(sId);
		model.addAttribute("surveyQuestion", surveyQuestion);
		return "survey/createsurvey";
	}
	
	@GetMapping("/course/survey/surveywelcomepage/createsurvey/display/addques/save")
	public String addQuesInSurvey(Model model, Long userID)
	{
		IQuestion questionDB = SystemConfig.instance().getQuestionService();
		User u=new User();
		u= CurrentUser.instance().getCurrentAuthenticatedUser();
		userID=u.getId();
		Course course = new Course();
		List<Question> ques=questionDB.loadInstructorQuestionsList(cId);
		model.addAttribute("questions", ques);
		model.addAttribute("course", course);
		ISurveyDB surveyDB = SystemConfig.instance().getSurveyDB();
		
		for (int i=0; i<quesIDs.size();i++) {
			surveyDB.saveQuestionInSurvey(quesIDs.get(i), cId);
		}	
		
		long sId=surveyDB.loadSurveyIdbyCourseID(cId);
		List<Question> surveyQuestion=surveyDB.loadQuestionBySurveyID(sId);
		model.addAttribute("surveyQuestion", surveyQuestion);
		model.addAttribute("displaylink", true);
		addition.clear();
		quesIDs.clear();
		return "survey/createsurvey";
	}

	@GetMapping("/course/survey/surveywelcomepage/createsurvey/display/addques/publish")
	public String publishSurvey(Model model, Long userID)
	{
		IQuestion questionDB = SystemConfig.instance().getQuestionService();
		User u=new User();
		u= CurrentUser.instance().getCurrentAuthenticatedUser();
		userID=u.getId();
		Course course = new Course();
		List<Question> ques=questionDB.loadInstructorQuestionsList(cId);
		model.addAttribute("questions", ques);
		model.addAttribute("course", course);
		ISurveyDB surveyDB = SystemConfig.instance().getSurveyDB();
		long sId=surveyDB.loadSurveyIdbyCourseID(cId);
		boolean checkRes=surveyDB.publishSurvey(sId);

		for (int i=0; i<quesIDs.size();i++) {
			surveyDB.saveQuestionInSurvey(quesIDs.get(i), cId);
		}	
		
		List<Question> surveyQuestion=surveyDB.loadQuestionBySurveyID(sId);
		model.addAttribute("surveyQuestion", surveyQuestion);
		model.addAttribute("displaylink", true);
		addition.clear();
		quesIDs.clear();
		
		if ((checkRes))
		{
			model.addAttribute("displaylink", true);
			return "survey/survey-published";
		}
		else
		{
			return "survey/survey-error";
		}		
	}

	@GetMapping("/course/survey/surveywelcomepage/createsurvey/display/delete")
	public String publishSurvey(Model model, Long userID,@RequestParam(name = ID) long questionID)
	{


		IQuestion questionDB = SystemConfig.instance().getQuestionService();
		User u=new User();
		u= CurrentUser.instance().getCurrentAuthenticatedUser();
		userID=u.getId();
		Course course = new Course();
		List<Question> ques=questionDB.loadInstructorQuestionsList(cId);
		model.addAttribute("questions", ques);
		model.addAttribute("course", course);
		ISurveyDB surveyDB = SystemConfig.instance().getSurveyDB();
		long sId=surveyDB.loadSurveyIdbyCourseID(cId);
		boolean checkRes= surveyDB.deleteSurveyQuestion(questionID,sId);
		List<Question> surveyQuestion=surveyDB.loadQuestionBySurveyID(sId);
		model.addAttribute("surveyQuestion", surveyQuestion);
		model.addAttribute("displaylink", true);
		addition.clear();
		quesIDs.clear();
		
		if ((checkRes))
		{
			model.addAttribute("displaylink", true);
			return "survey/createsurvey";
		}
		else
		{
			return "survey/survey-error";
		}		
	}

}

