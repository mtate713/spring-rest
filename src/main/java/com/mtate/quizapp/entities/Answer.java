package com.mtate.quizapp.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "answer")
public class Answer {
	public Answer() {
	}

	public Answer(String text, boolean is_correct, Integer question_id) {
		this.text = text;
		this.is_correct = is_correct;
		this.question_id = question_id;
	}

	public Answer(Integer id, String text, boolean is_correct, Integer question_id) {
		this.id = id;
		this.text = text;
		this.is_correct = is_correct;
		this.question_id = question_id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "text")
	private String text;

	@Column(name = "is_correct")
	private boolean is_correct;

	@Column(name = "question_id")
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

	public boolean isIs_correct() {
		return is_correct;
	}

	public void setIs_correct(boolean is_correct) {
		this.is_correct = is_correct;
	}

	public Integer getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
	}
}
