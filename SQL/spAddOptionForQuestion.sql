DELIMITER $$

DROP PROCEDURE IF EXISTS spAddOptionForQuestion $$

CREATE PROCEDURE spAddOptionForQuestion(
	IN optionText VARCHAR(255),
    IN optionValue INT,
    IN questionName VARCHAR(45)
)
BEGIN
	SELECT questionID FROM Question
		WHERE questionTitle = questionName
        INTO @questionID;

	INSERT INTO QuestionOption(optionText, optionValue, questionID)
		VALUES (optionText, optionValue, @questionID);
END $$

DELIMITER ;