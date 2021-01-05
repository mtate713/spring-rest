package com.mtate.quizapp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.array.IntArrayType;


@Entity
@Table(name = "question")
@TypeDef(
	    name = "int-array",
	    typeClass = IntArrayType.class
	)
public class Question {

	public Question() {
	}

	public Question(String text, Integer quiz_id) {
		this.text = text;
		this.quiz_id = quiz_id;
	}

	public Question(String text, Integer[] answers, Integer quiz_id) {
		this.text = text;
		this.answers = answers;
		this.quiz_id = quiz_id;
	}

	public Question(Integer id, String text, Integer[] answers, Integer quiz_id) {
		this.id = id;
		this.text = text;
		this.answers = answers;
		this.quiz_id = quiz_id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "text")
	private String text;

	@Column(name = "quiz_id")
	private Integer quiz_id;

	@Type(type = "int-array")
	@Column(name = "answers", columnDefinition = "integer[]")
	private Integer[] answers;
	


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

	public Integer[] getAnswers() {
		return answers;
	}

	public void setAnswers(Integer[] answers) {
		this.answers = answers;
	}

	public Integer getQuiz_id() {
		return quiz_id;
	}

	public void setQuiz_id(Integer quiz_id) {
		this.quiz_id = quiz_id;
	}
}
