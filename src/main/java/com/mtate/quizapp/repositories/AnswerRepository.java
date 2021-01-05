package com.mtate.quizapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtate.quizapp.entities.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer>{

}
