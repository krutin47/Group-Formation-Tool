package CSCI5308.GroupFormationTool.Questions;

import java.util.List;

public interface IChoice {
    public List<Choice> loadChoicesByQuesID(long id);
}
