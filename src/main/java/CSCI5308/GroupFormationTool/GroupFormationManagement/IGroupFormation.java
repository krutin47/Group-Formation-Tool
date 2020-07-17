package CSCI5308.GroupFormationTool.GroupFormationManagement;

import CSCI5308.GroupFormationTool.Answers.AnswerStudentMapper;

import java.util.List;

public interface IGroupFormation {
    List<Group> createGroup(List<AnswerStudentMapper> answerStudentMapper, int groupSize);
    List<Group> createRandomGroup(List<AnswerStudentMapper> answerStudentMapper, int groupSize);
}
