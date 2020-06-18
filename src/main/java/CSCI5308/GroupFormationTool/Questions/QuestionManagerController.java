package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping
public class QuestionManagerController {

    private static final String ID = "id";
    private static long storingValue = 0;

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

    @GetMapping("/question/questionmanager")
    public ModelAndView viewQuestions(ModelAndView model, Long userID)
    {
        IQuestion questionDB = SystemConfig.instance().getQuestionService();
        User u=new User();
        u= CurrentUser.instance().getCurrentAuthenticatedUser();
        userID=u.getId();
        List<Question> ques=questionDB.loadQuestionByInstID(userID);
        model.addObject("questions", ques);
        model.setViewName("question/questionmanager");
        return model;
    }


    @GetMapping("/question/questionmanager/sortbydate")
    public ModelAndView questionSortByDate(ModelAndView model, Long userID)
    {
        IQuestion questionDB = SystemConfig.instance().getQuestionService();
        User u=new User();
        u=CurrentUser.instance().getCurrentAuthenticatedUser();
        userID=u.getId();
        List<Question> ques=questionDB.loadQuestionByInstID(userID);
        Collections.sort(ques);
        model.addObject("questions", ques);
        model.setViewName("question/questionmanager");
        return model;
    }

    @GetMapping("/question/questionmanager/sortbytitle")
    public ModelAndView questionSortByTitle(ModelAndView model, Long userID)
    {
        IQuestion questionDB = SystemConfig.instance().getQuestionService();
        Question q = new Question();
        User u=new User();
        u=CurrentUser.instance().getCurrentAuthenticatedUser();
        userID=u.getId();
        List<Question> ques=questionDB.loadQuestionByInstID(userID);
        Collections.sort(ques,q.sortTitle);
        model.addObject("questions", ques);
        model.setViewName("question/questionmanager");
        return model;
    }


    @GetMapping("/question/questionmanager/deletequestion")
    public ModelAndView deleteQuestion(Model model, @RequestParam(name = ID) long questionID)
    {

        IQuestion questionDB = SystemConfig.instance().getQuestionService();
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
        IQuestion questionDB = SystemConfig.instance().getQuestionService();
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
