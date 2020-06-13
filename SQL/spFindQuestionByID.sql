DELIMITER $$

DROP PROCEDURE IF EXISTS spFindQuestionByID $$

CREATE PROCEDURE spFindQuestionByID (
    IN qID BIGINT
)
BEGIN
    SELECT questionID, questionTitle, questionText
    FROM Question
    WHERE Question.QuestionID = qID;
END $$

DELIMITER ;