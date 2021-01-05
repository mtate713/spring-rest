package com.mtate.quizapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtate.quizapp.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
