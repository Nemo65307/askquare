package com.nemo.askquare.repository;

import com.nemo.askquare.annotation.ReturnType;
import com.nemo.askquare.annotation.Select;
import com.nemo.askquare.model.Test;

import java.util.List;

public interface TestRepository {

    @Select(sql = "select * from test order by id")
    @ReturnType(entityClass = Test.class)
    List<Test> findAll();

}
