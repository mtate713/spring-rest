package com.mtate.quizapp.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class AnswerDTO  {

    public AnswerDTO() {
    }

    public AnswerDTO(String text, Integer question_id) {
        this.text = text;
        this.is_correct = is_correct;
        this.question_id = question_id;
    }

    public AnswerDTO(String text, boolean is_correct, Integer question_id) {
        this.text = text;
        this.is_correct = is_correct;
        this.question_id = question_id;
    }

    public AnswerDTO(Integer id, String text, boolean is_correct, Integer question_id) {
        this.id = id;
        this.text = text;
        this.is_correct = is_correct;
        this.question_id = question_id;
    }

    private Integer id;

    private String text;

    private boolean is_correct;

    private Integer question_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isIs_Correct() {
        return is_correct;
    }

    public void setIs_Correct(boolean is_correct) {
        this.is_correct = is_correct;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public String toString() {
        String result =  "{Answer ID: " + Integer.toString(this.getId()) + ", Text: " + this.getText() + "}";
        return result;

    }
}



