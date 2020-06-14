package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import java.util.List;

public class QuestionController {



    @RequestMapping(value = "/createquestion", method = RequestMethod.POST)
    public ModelAndView createQuestion(@RequestParam(name = "Title") String questionTitle,
                                       @RequestParam(name = "Text") String questionText,
                                       @RequestParam(name ="typeID" ) long typeID)
    {

          boolean success=false;
          IQuestionPersistence questionDB=SystemConfig.instance().getQuestionDB();
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
              ModelAndView mav = new ModelAndView("redirect:/instructor/createquestion/type" + typeID);  //redirect based on type of question
              return mav;
          }

          else
          {
              ModelAndView mav = new ModelAndView("redirect:/error");  //redirect based on type of question
              return mav;
          }


    }
}
