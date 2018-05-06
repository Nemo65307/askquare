package com.nemo.askquare.repository;

import com.nemo.askquare.annotation.ReturnType;
import com.nemo.askquare.annotation.Select;
import com.nemo.askquare.model.Answer;

import java.util.List;

public interface AnswerRepository {

    @Select(sql = "select * from answer order by id")
    @ReturnType(entityClass = Answer.class)
    List<Answer> findAll();

    @Select(sql = "select * from answer where id_question=?")
    @ReturnType(entityClass = Answer.class)
    List<Answer> findByQuestionId(long questionId);

    @Select(sql = "select answer.id, answer.id_question, answer.name, answer.correct\n" +
            "from answer\n" +
            "join question on answer.id_question = question.id\n" +
            "join test on question.id_test = test.id\n" +
            "where test.id = ?")
    @ReturnType(entityClass = Answer.class)
    List<Answer> findByTestId(long testId);

}
