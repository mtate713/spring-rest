package com.mtate.quizapp.dto;

import java.io.Serializable;
import java.util.List;

public class QuestionDTO  {

    public QuestionDTO(){

    }

    public QuestionDTO(String text, List<AnswerDTO> answers) {
        this.text = text;
        this.answers = answers;
    }

    public QuestionDTO(Integer id, String text, List<AnswerDTO> answers) {
        this.id = id;
        this.text = text;
        this.answers = answers;
    }

    public QuestionDTO(String text, List<AnswerDTO> answers, Integer quiz_id) {
        this.text = text;
        this.answers = answers;
        this.quiz_id = quiz_id;
    }

    public QuestionDTO(Integer id, String text, List<AnswerDTO> answers, Integer quiz_id) {
        this.id = id;
        this.text = text;
        this.answers = answers;
        this.quiz_id = quiz_id;
    }

    private Integer id;

    private String text;

    private List<AnswerDTO> answers;

    private Integer quiz_id;


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

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    public Integer getQuizId() {
        return quiz_id;
    }

    public void setQuizId(Integer quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String toString() {
        String result =  "{Question ID: " + Integer.toString(this.getId()) + ", Text: " + this.getText();
        if (this.getAnswers().isEmpty())
            result += (", Answers: None}" + "\n");
        else
            result += (this.getAnswers().toString() + "\n");
        return result;

    }


}
