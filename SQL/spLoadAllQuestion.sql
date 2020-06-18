DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadAllQuestions $$

CREATE PROCEDURE spLoadAllQuestions ()
BEGIN
	SELECT questionID, questionTitle, questionText, creationDate
    FROM Question
    ORDER BY questionID ASC;
END $$

DELIMITER ;