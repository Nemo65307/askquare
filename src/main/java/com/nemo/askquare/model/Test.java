package com.nemo.askquare.model;

import com.nemo.askquare.annotation.Column;
import com.nemo.askquare.annotation.InsertIgnore;

public class Test {

    @InsertIgnore
    private Long id;

    @Column("id_account")
    private Long accountId;

    private String name;
    private String description;

    @Column("time_per_question")
    private Integer timePerQuestion;

    public Test() {
    }

    public Test(Long accountId, String name, String description, Integer timePerQuestion) {
        this.accountId = accountId;
        this.name = name;
        this.description = description;
        this.timePerQuestion = timePerQuestion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTimePerQuestion() {
        return timePerQuestion;
    }

    public void setTimePerQuestion(Integer timePerQuestion) {
        this.timePerQuestion = timePerQuestion;
    }
}
