DELIMITER $$

DROP PROCEDURE IF EXISTS spDeleteQuestion $$

CREATE PROCEDURE spDeleteQuestion (
    IN id BIGINT
)
BEGIN
    DELETE FROM Question
    WHERE Question.questionID = id;

    DELETE FROM QuestionOption
    WHERE QuestionOption.questionID = id;

    DELETE FROM QuestionTypeMapper
    WHERE QuestionTypeMapper.questionID = id;
END $$

DELIMITER ;