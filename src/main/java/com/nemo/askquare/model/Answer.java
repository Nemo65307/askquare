package com.nemo.askquare.model;

import com.nemo.askquare.annotation.Column;
import com.nemo.askquare.annotation.InsertIgnore;

public class Answer {

    @InsertIgnore
    private Long id;

    @Column("id_question")
    private Long questionId;

    private String name;

    @Column("correct")
    private Boolean isCorrect;

    public Answer() {
    }

    public Answer(Long questionId, String name, Boolean isCorrect) {
        this.questionId = questionId;
        this.name = name;
        this.isCorrect = isCorrect;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

}
