package CSCI5308.GroupFormationTool.Answers;

import CSCI5308.GroupFormationTool.Questions.Choice;
import CSCI5308.GroupFormationTool.Questions.IChoice;
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
    public static long surveyid=0;
    private static final String ANSWER = "answer";


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
            mav=new ModelAndView("survey/survey");    //if survey is published
            return mav;
        }
        else
        {
            mav=new ModelAndView("survey/Nosurvey");   //if survey for the subject is not published
            return mav;
        }

    }


    //The survey page
    @GetMapping("/survey/survey")
    public String Survey(Model model)
    {

        IStudentSurvey studentSurvey=SystemConfig.instance().getStudentSurvey();
        IQuestion q=SystemConfig.instance().getQuestionService();
        surveyid = studentSurvey.getSurveyIDfromCourse(storeval);    //load Survey ID by course ID
        List<Long> questionID = q.loadQuestionIDbySurveyID(surveyid);   //Load list of question ID's linked to survey
        List<Question> questions = new ArrayList<>();      //list to contain all questions linked to survey
        for(long l:questionID)     //Iterate over different question id's
        {
          questions.add(q.loadQuestionbyID(l));  //Store all question objects of one surveyID,in one list
        }
        //PASS: serveyID to view and then fetch it.
        model.addAttribute("questions",questions);


        for(Question qt:questions)  //checking the type of question
        {
            if(qt.getTypeID()==1) //numeric
            {
                model.addAttribute("displayNum", true);

            }
            if(qt.getTypeID()==2) //Multiple choice,choose one
            {
                model.addAttribute("displayMul1",true);
            }
            if(qt.getTypeID()==3) //Multiple choice, checkboxes
            {
                model.addAttribute("displayMul2",true);
            }
            if(qt.getTypeID()==4) //free text
            {
                model.addAttribute("displayFree",true);
            }
        }

        return "/survey/survey";
    }


}
