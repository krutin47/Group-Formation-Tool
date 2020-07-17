package CSCI5308.GroupFormationTool.GroupFormationManagement;

import CSCI5308.GroupFormationTool.Answers.AnswerStudentMapper;
import CSCI5308.GroupFormationTool.Answers.IAnswerStudentMapperDB;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.SurveyRudra.ISurveyPersistence;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class GroupFormationController {

    public IGroupFormation iGroupFormation = SystemConfig.instance().getGroupFormation();
    public ISurveyPersistence iSurveyPersistence = SystemConfig.instance().getSurveyPersistence();
    public ICoursePersistence iCoursePersistence = SystemConfig.instance().getCourseDB();
    public IAnswerStudentMapperDB studentMapperDB = SystemConfig.instance().getStudentMapperDB();

    @GetMapping("/survey/groupformation")
    ModelAndView groupFormationPage(
            @RequestParam(value = "cid") long id
    ){
        ModelAndView modelAndView = new ModelAndView();
        boolean check = iSurveyPersistence.isPublished(id);
        Course course = new Course();
        iCoursePersistence.loadCourseByID(id, course);
        if (check){
            modelAndView.addObject("course",course);
            modelAndView.setViewName("group-management/groupmanagement");
        } else {
            modelAndView.addObject("course", course);
            modelAndView.setViewName("group-management/survey-not-available");
        }
        return modelAndView;
    }

    @PostMapping("/group/creategroups")
    ModelAndView createGroupRequest(
        @RequestParam(value = "courseID") long courseID,
        @RequestParam(value = "groupSize") String size
    ){
        int groupSize = Integer.parseInt(size);
        List<AnswerStudentMapper> studentMappers = new ArrayList<>();
        List<Group> groups = new ArrayList<>();
        studentMappers = studentMapperDB.getSurveyResponsesByCourseID(courseID);
        groups = iGroupFormation.createRandomGroup(studentMappers, groupSize);
        System.out.println("Group Size:"+ groups.size());
        ModelAndView modelAndView = new ModelAndView();
        if(groups == null){
            modelAndView.addObject("noOfGroups",0);
        }
        else{
            modelAndView.addObject("noOfGroups",groups.size());
        }
        modelAndView.addObject("groups", groups);
        modelAndView.setViewName("group-management/groups");
        return modelAndView;
    }
}
