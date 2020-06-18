package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
            @RequestParam(value = "bannerID") String bannerID,
            ModelAndView modelAndView
    ){
        List<Choice> choices = new ArrayList<>();
        if(questionType.equalsIgnoreCase("Free text")  || questionType.equalsIgnoreCase("Numeric")){
            Question question = new Question(
                    questionTitle,
                    questionText,
                    questionType,
                    bannerID,
                    choices
            );
            Boolean questionAddition = questionDB.createSimpleQuestion(question);
            if(!questionAddition){
                modelAndView.setViewName("question/question-error");
                return modelAndView;
            }
            modelAndView.setViewName("question/question-added");
        }
        else {
            choices.add(new Choice());
            Question question = new Question(questionTitle, questionText, questionType, bannerID, choices);
            modelAndView = modelAndView.addObject("Question", question);
            modelAndView.setViewName("question/optionpage");
        }
        return modelAndView;
    }


    @PostMapping("/question/addquestionoptions")
    public ModelAndView addQuestionOptions(@ModelAttribute(value = "Question") Question question,
                                           ModelAndView modelAndView){
        if(question.getChoices().isEmpty()){
            modelAndView.setViewName("question/question-error");
            return modelAndView;
        }
        Boolean saveQuestionOption = questionDB.createMCQuestion(question);
        System.out.println(saveQuestionOption);
        if(!saveQuestionOption){
            modelAndView.setViewName("question/question-error");
            return modelAndView;
        }
        modelAndView.setViewName("question/question-added");
        return modelAndView;
    }

    @RequestMapping(value = "/question/addquestionoptions", params = {"addRow"})
    public ModelAndView addOptions(final Question question,
                                   final BindingResult bindingResult,
                                   final ModelAndView modelAndView){
        System.out.println(question);
        question.getChoices().add(new Choice());
        modelAndView.addObject("Question", question);
        modelAndView.setViewName("question/optionpage");
        return modelAndView;
    }

}
