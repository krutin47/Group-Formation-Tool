package CSCI5308.GroupFormationTool.Question;
import java.util.Arrays;
import java.util.List;

public class QuestionType implements ITypeQuestion {


    public List<Type> loadAllTypesInQuestion(Question question)
    {

       return Arrays.asList(Type.values());
    }
}
