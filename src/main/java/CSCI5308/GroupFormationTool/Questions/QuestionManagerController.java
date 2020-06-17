package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping
public class QuestionManagerController {

    public IQuestion questionDB = SystemConfig.instance().getQuestionService();
    public IQuestionType questionType = SystemConfig.instance().getQuestionTypeService();

    @GetMapping("/question/questioncreationpage")
    public ModelAndView questionCreationPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView = modelAndView.addObject("QuestionType", questionType.loadAllQuestionTypes());
        modelAndView.setViewName("question/createquestion");
        return modelAndView;
    }

    @PostMapping("/question/addquestion")
    public ModelAndView nextQuestionPage(
            @RequestParam(value = "questionTitle") String questionTitle,
            @RequestParam(value = "questionText") String questionText,
            @RequestParam(value = "questionTypeList") String questionType,
            @RequestParam(value = "bannerID") String bannerID
    ){
        ModelAndView modelAndView = new ModelAndView();
        if(questionType.equalsIgnoreCase("Free text")  || questionType.equalsIgnoreCase("Numeric")){
            Question question = new Question(
                    questionTitle,
                    questionText,
                    questionType,
                    bannerID
            );
            Boolean questionAddition = questionDB.createSimpleQuestion(question);
            modelAndView.setViewName("question/question-added");
        }
        else {
            modelAndView = modelAndView.addObject("Question",new Question(questionTitle, questionText, questionType, bannerID));
            modelAndView.setViewName("question/optionpage");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/addoptions", params = {"addRow"})
    public ModelAndView addOptions(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
}
