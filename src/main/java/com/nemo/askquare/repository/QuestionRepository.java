package com.nemo.askquare.repository;

import com.nemo.askquare.annotation.ReturnType;
import com.nemo.askquare.annotation.Select;
import com.nemo.askquare.model.Question;

import java.util.List;

public interface QuestionRepository {

    @Select(sql = "select * from question order by id")
    @ReturnType(entityClass = Question.class)
    List<Question> findAll();

    @Select(sql = "select * from question where id_test=?")
    @ReturnType(entityClass = Question.class)
    List<Question> findByTestId(long testId);

}
