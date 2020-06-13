DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateQuestion $$

CREATE PROCEDURE spCreateQuestion (
    IN title VARCHAR(45),
    IN text VARCHAR(1000),
    OUT questionid BIGINT
)
BEGIN
    INSERT INTO Question(questionTitle,questionText)
    VALUES (title,text);
    SET questionid = LAST_INSERT_ID();
END $$

DELIMITER ;