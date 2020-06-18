package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Collections;
import java.util.List;

@Controller
public class QuestionController {
    private static final String ID = "id";
    private static long storingValue = 0;

    @GetMapping("/createquestion")
    public String questionForm(Model model) {
        model.addAttribute("question", new Question());
        return "createquestion";
    }



    @RequestMapping(value = "/createquestion", method = RequestMethod.POST)
    public ModelAndView createQuestion(@RequestParam(name = "Title") String questionTitle,
                                       @RequestParam(name = "Text") String questionText,
                                       @RequestParam(name ="typeID" ) long typeID)
    {

        boolean success=false;

        IQuestionPersistence questionDB=SystemConfig.instance().getQuestionDB();   //to create the question in DB
        Question q=new Question();
        if(q.isTextvalid(questionText) && (q.isTitlevalid(questionTitle)))  //check if input is right
        {
            q.setQuestionTitle(questionTitle);
            q.setQuestionText(questionText);
            q.setTypeID(typeID);
            if (questionDB.createQuestion(q))
            {
                success = true;    //confirmed, question was created

            }
        }


        //Check if question was created
        if(success)
        {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("type"+typeID);       //go to page based on typeID
            return mav;   //return model and view
        }

        else
        {
            ModelAndView mav = new ModelAndView("redirect:/error");  //redirect based on type of question
            return mav;
        }


    }


    @GetMapping("/question/questionmanager")
    public String viewQuestions(Model model, Long userID)
    {
        IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
        User u=new User();
        u=CurrentUser.instance().getCurrentAuthenticatedUser();
        userID=u.getId();
        List<Question> ques=questionDB.loadQuestionByInstID(userID);
        model.addAttribute("questions", ques);
        return "question/questionmanager";

    }


    @GetMapping("/question/questionmanager/sortbydate")
    public String heySort(Model model, Long userID)
    {
        IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
        User u=new User();
        u=CurrentUser.instance().getCurrentAuthenticatedUser();
        userID=u.getId();
        List<Question> ques=questionDB.loadQuestionByInstID(userID);
        Collections.sort(ques);
        model.addAttribute("questions", ques);
        return "question/questionmanager";

    }
    @GetMapping("/question/questionmanager/sortbytitle")
    public String heySort2(Model model, Long userID)
    {
        IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
        Question q = new Question();
        User u=new User();
        u=CurrentUser.instance().getCurrentAuthenticatedUser();
        userID=u.getId();
        List<Question> ques=questionDB.loadQuestionByInstID(userID);
        Collections.sort(ques,q.sortTitle);
        model.addAttribute("questions", ques);
        return "question/questionmanager";

    }


    @GetMapping("/question/questionmanager/deletequestion")
    public ModelAndView deleteQuestion(Model model, @RequestParam(name = ID) long questionID)
    {

        IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
        Question q = new Question();
        List<Question> ques=questionDB.loadQuestionByQID(questionID);
        model.addAttribute("questions", ques);
        model.addAttribute("questionID", questionID);
        storingValue=questionID;
        q.setQuestionID(questionID);
        ModelAndView mav = new ModelAndView("question/deletion");
        return mav;
    }
    @GetMapping("/question/questionmanager/deletequestion/confirm")
    public ModelAndView deleteQuestionConfirm(Model model, Long questionID)
    {
        IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
        Question q = new Question();
        questionID=storingValue;
        model.addAttribute("questionID", questionID);
        q.setQuestionID(questionID);
        q.deleteQuestion(questionDB);
        ModelAndView mav = new ModelAndView("redirect:/question/questionmanager");
        return mav;
    }
    @GetMapping("/question/questionmanager/deletequestion/cancel")
    public ModelAndView deleteQuestionCancel()
    {
        ModelAndView mav = new ModelAndView("redirect:/question/questionmanager");
        return mav;
    }

}
