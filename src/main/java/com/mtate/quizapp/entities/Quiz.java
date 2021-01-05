package com.mtate.quizapp.entities;

import java.io.Serializable;
import java.util.List;


import javax.persistence.*;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.array.IntArrayType;


@Entity(name = "quiz")
@Table(name = "quiz")
@TypeDef(
	    name = "int-array",
	    typeClass = IntArrayType.class
	)
public class Quiz implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Type(type = "int-array")
	@Column(name = "questions", columnDefinition = "integer[]")
	private Integer[] questions;

	public Quiz() {
	}

	public Quiz(String name) {
		this.name = name;
	}

	public Quiz(String name, Integer[] questions) {
		this.name = name;
		this.questions = questions;
	}

	public Quiz(Integer id, String name, Integer[] questions) {
		this.id = id;
		this.name = name;
		this.questions = questions;
	}

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

	public Integer[] getQuestions() {
		return questions;
	}

	public void setQuestions(Integer[] questions) {
		this.questions = questions;
	}
}
