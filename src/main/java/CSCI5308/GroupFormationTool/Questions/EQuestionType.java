package CSCI5308.GroupFormationTool.Questions;

public enum EQuestionType {
    Numeric
            {
                public String toString()
                {
                    return "Numeric";
                }
            },

    Text
            {
                public String toString()
                {
                    return "Text";
                }
            },

    MultipleChoice_ChooseOne
            {
                public String toString()
                {
                    return "Multiple Choice, Choose One";
                }
            },

    MultipleChoice_ChooseMultiple
            {
                public String toString()
                {
                    return "Multiple Choice, Choose Multiple";
                }
            }
}