package com.mtate.quizapp.services;

import com.mtate.quizapp.dto.AnswerDTO;
import com.mtate.quizapp.dto.QuestionDTO;
import com.mtate.quizapp.entities.Answer;
import com.mtate.quizapp.entities.Question;
import com.mtate.quizapp.entities.Quiz;
import com.mtate.quizapp.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

import com.mtate.quizapp.repositories.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

	private QuestionRepository questionRepository;

	private QuestionMapper questionMapper;

	private AnswerService answerService;

	public QuestionService(QuestionRepository questionRepository, QuestionMapper questionMapper, AnswerService answerService) {

		this.questionRepository = questionRepository;
		this.questionMapper = questionMapper;
		this.answerService = answerService;

	}

	public QuestionMapper getQuestionMapper() {
		return this.questionMapper;
	}

    public Question getQuestion(Integer id) {
		return questionRepository.getOne(id);
    }


	public void deleteQuestion(Integer id) {
		Question deleted = questionRepository.getOne(id);
		if (deleted.getAnswers() != null) {
			Integer[] answers = deleted.getAnswers();
			for (int i = 0; i < answers.length; i++) {
				answerService.deleteAnswer(answers[i]);
			}
		}
		questionRepository.deleteById(id);
		questionRepository.flush();

	}

	public void QuestionFlush() {
		questionRepository.flush();
	}

	public Question addQuestion(Question q) {
		Question question = questionRepository.saveAndFlush(q);
		if (q.getAnswers() != null) {
			question.setAnswers(q.getAnswers());
		}
		questionRepository.flush();
		return question;


	}


}
