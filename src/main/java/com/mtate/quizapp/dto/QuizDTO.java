package com.mtate.quizapp.dto;

import java.io.Serializable;
import java.util.List;

public class QuizDTO {
    public QuizDTO() {

    }

    public QuizDTO(String name) {
        this.name = name;
    }

    public QuizDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


    public QuizDTO(String name, List<QuestionDTO> questions) {
        this.name = name;
        this.questions = questions;
    }

    public QuizDTO(Integer id, String name, List<QuestionDTO> questions) {
        this.id = id;
        this.name = name;
        this.questions = questions;
    }

    private Integer id;

    private String name;

    private List<QuestionDTO> questions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public String toString() {
        String result =  "{ID: " + Integer.toString(this.getId()) + ", Name: " + this.getName();
        if (this.getQuestions().isEmpty())
            result += ", Questions: None}\n";
        else
            result += this.getQuestions().toString() +"\n";
        return result;

    }
}
