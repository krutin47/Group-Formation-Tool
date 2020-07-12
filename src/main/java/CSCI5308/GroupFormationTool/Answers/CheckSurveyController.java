package CSCI5308.GroupFormationTool.Answers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.Courses.*;
import CSCI5308.GroupFormationTool.AccessControl.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping
@Controller
public class CheckSurveyController {

    private static final String ID = "id";

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
        if((courseCheck.checkCourseSurvey(courseID)))
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

}
