DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateSimpleQuestion $$

CREATE PROCEDURE spCreateSimpleQuestion(
	IN questionTitle VARCHAR(45),
    IN questionText VARCHAR(1000),
    IN typeName VARCHAR(45)
)
BEGIN
	SELECT SYSDATE() INTO @creation_date;
	INSERT INTO Question(questionTitle, questionText, creationDate)
		VALUES (questionTitle, questionText, @creation_date);
	SET @questionID = LAST_INSERT_ID();

    SELECT typeID FROM QuestionType
		WHERE type = typeName
        INTO @typeID;

    INSERT INTO QuestionTypeMapper(questionID,typeID)
		VALUES (@questionID, @typeID);
END $$

DELIMITER ;