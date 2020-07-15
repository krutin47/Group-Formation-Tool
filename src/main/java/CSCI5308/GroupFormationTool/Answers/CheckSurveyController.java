package CSCI5308.GroupFormationTool.Answers;

import CSCI5308.GroupFormationTool.Questions.IQuestion;
import CSCI5308.GroupFormationTool.Questions.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.Courses.*;
import CSCI5308.GroupFormationTool.AccessControl.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping
@Controller
public class CheckSurveyController {

    private static final String ID = "id";
    public static long storeval=0;


    //checks courses registered with the student
    @GetMapping("/survey/checksurvey")
    public String checkCourse(Model model)
    {
        Long UserID;
        ICoursePersistence courseCheck= SystemConfig.instance().getCourseDB();
        User u=new User();
        u=CurrentUser.instance().getCurrentAuthenticatedUser();
        UserID=u.getID();
        List<Course> c=courseCheck.loadCoursebyUser(UserID);
        model.addAttribute("courses", c);

        return "survey/checksurvey" ;
    }


    //checks if the survey for a course is published
    @GetMapping("/survey/course")
    public ModelAndView checkSurvey(@RequestParam(name = ID) Long courseID)
    {

        ModelAndView mav;
        ICoursePersistence courseCheck= SystemConfig.instance().getCourseDB();
        storeval=courseID;

        if((courseCheck.checkCourseSurvey(courseID)))  //checking if a course exists
        {
            mav=new ModelAndView("survey/survey");
            return mav;
        }
        else
        {
            mav=new ModelAndView("survey/Nosurvey");
            return mav;
        }

    }


    //The survey page
    @GetMapping("/survey/survey/question")
    public String Survey(Model model)
    {

        IStudentSurvey studentSurvey=SystemConfig.instance().getStudentSurvey();
        IQuestion q=SystemConfig.instance().getQuestionService();

        List<Question> questions=new ArrayList<>();
        long surveyid=studentSurvey.getSurveyIDfromCourse(storeval);    //load Survey ID by course ID
        IQuestion question=SystemConfig.instance().getQuestionService();
        List<Long> questionID=question.loadQuestionIDbySurveyID(surveyid);   //Load question ID's linked to survey
        for(long l:questionID)
        {
          questions.add(q.loadQuestionbyID(l));  //Store all question objects in one list
        }
        model.addAttribute("questions",questions);
        return "/survey/survey";
    }


}
