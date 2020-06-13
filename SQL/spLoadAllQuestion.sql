DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadAllQuestions $$

CREATE PROCEDURE spLoadAllQuestions ()
BEGIN
	SELECT questionID, questionTitle, questionText
    FROM Question
    ORDER BY title ASC;
END $$

DELIMITER ;