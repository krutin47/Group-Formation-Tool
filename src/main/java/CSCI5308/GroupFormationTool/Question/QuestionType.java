package CSCI5308.GroupFormationTool.Question;
import java.util.Arrays;
import java.util.List;

public class QuestionType implements ITypeQuestion {


    public List<Type> loadAllTypesInQuestion()
    {

       return Arrays.asList(Type.values());
    }



    public long setTypeID(Question q,String type) {

        long id=0;

        if(type.equalsIgnoreCase("numeric"))
        {
            id=1;
        }

        else if(type.equalsIgnoreCase("text"))
        {
            id=2;
        }
        else if(type.equalsIgnoreCase("Multiple Choice, Choose One"))
        {
            id=3;
        }
        else
        {
            id=4;
        }
        return id;
    }
}
