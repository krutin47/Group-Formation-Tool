package CSCI5308.GroupFormationTool.Survey;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SurveyDBTest {

    @Test
    void createSurvey() {
        Survey survey = new Survey("Title", "Y");
        assertNotNull(survey.getSTitle());
        assertNotNull(survey.getIsPublished());
    }

    @Test
    void publishSurvey() {
    }

    @Test
    void deleteSurveyQuestion() {
    }

    @Test
    void loadSurveyIdbyCourseID() {
    }

    @Test
    void checkSurveyExists() {
    }

    @Test
    void checkSurveyIsPublished() {
    }

    @Test
    void checkifQuestionInSurveyExists() {
    }

    @Test
    void loadQuestionBySurveyID() {
    }

    @Test
    void saveQuestionInSurvey() {
    }
}