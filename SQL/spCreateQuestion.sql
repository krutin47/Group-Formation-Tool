DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateQuestion $$

CREATE PROCEDURE spCreateQuestion (
    IN questionTitle VARCHAR(45),
    IN questionText VARCHAR(1000),
    IN typeID BIGINT(20),
    OUT creationDate DATETIME,
    OUT questionID BIGINT
)
BEGIN

    select sysdate() into @creation_date;

    INSERT INTO Question(questionTitle,questionText, creationDate)
    VALUES (questionTitle,questionText);
    SET questionID = LAST_INSERT_ID();

    SELECT QuestionType.typeID
        INTO @type
    FROM QuestionType
        WHERE QuestionType.typeID=typeID;

    INSERT INTO QuestionTypeMapper(questionID,typeID)
    VALUES(questionID,@type);

END $$

DELIMITER ;