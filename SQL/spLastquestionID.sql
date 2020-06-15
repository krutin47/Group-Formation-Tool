DELIMITER $$

DROP PROCEDURE IF EXISTS spLastquestionID $$

CREATE PROCEDURE spLastquestionID ()
BEGIN
    SELECT questionID
    FROM Question
    order by questionID DESC LIMIT 1;

END $$

DELIMITER ;