package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import java.util.List;

@Controller
public class QuestionController {


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
              q.setTitle(questionTitle);
              q.setText(questionText);
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
}
