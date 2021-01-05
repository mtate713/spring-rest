package com.mtate.quizapp.services;

import com.mtate.quizapp.dto.AnswerDTO;
import com.mtate.quizapp.dto.QuestionDTO;
import com.mtate.quizapp.entities.Answer;
import com.mtate.quizapp.entities.Question;
import org.springframework.stereotype.Service;

import com.mtate.quizapp.repositories.AnswerRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class AnswerService {
	
	private AnswerRepository answerRepository;

	public AnswerService(AnswerRepository answerRepository) {

		this.answerRepository = answerRepository;
	}

	public Answer getAnswer(Integer id) { return answerRepository.getOne(id); }



	public void deleteAnswer(Integer id) {
		answerRepository.deleteById(id);
		answerRepository.flush();

	}


}
