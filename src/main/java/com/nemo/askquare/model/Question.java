package com.nemo.askquare.model;

import com.nemo.askquare.annotation.Column;
import com.nemo.askquare.annotation.InsertIgnore;

public class Question {

    @InsertIgnore
    private Long id;

    @Column("id_test")
    private Long testId;

    private String name;

    public Question() {
    }

    public Question(Long testId, String name) {
        this.testId = testId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
