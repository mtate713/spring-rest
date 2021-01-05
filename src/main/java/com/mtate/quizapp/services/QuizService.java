package com.mtate.quizapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.mtate.quizapp.entities.Question;
import com.mtate.quizapp.entities.Quiz;
import com.mtate.quizapp.repositories.QuizRepository;

@Service
public class QuizService {


	public QuizRepository quizRepository;
	
	private QuestionService questionService;
	
	private AnswerService answerService;


	public QuizService(QuizRepository quizRepository, QuestionService questionService, AnswerService answerService) {
		this.quizRepository = quizRepository;
		this.questionService = questionService;
		this.answerService = answerService;

	}

	public List<Quiz> getQuizzes() {
		List<Quiz> quizzes = quizRepository.findAll();
		return quizzes;
	}

	public Quiz addQuiz(Quiz quiz) {
		Quiz q;
		if (quiz.getQuestions() != null) {
			q = quizRepository.saveAndFlush(new Quiz(quiz.getName()));
			q.setQuestions(quiz.getQuestions());
			for (int i = 0; i < q.getQuestions().length; i++) {
				Question question = questionService.getQuestion(q.getQuestions()[i]);
				question.setQuiz_id(q.getId());
				questionService.QuestionFlush();
			}
		}
		else
			q = quizRepository.saveAndFlush(new Quiz(quiz.getName()));

		quizRepository.flush();
		return q;
	}

	public Quiz deleteQuiz(Integer id) {
		Quiz deleted = quizRepository.getOne(id);
		if (deleted.getQuestions() != null) {
			Integer[] questions = quizRepository.getOne(id).getQuestions();
			for (int i = 0; i < questions.length; i++)
				deleteQuestion(id, questions[i]);
		}
		quizRepository.deleteById(id);
		quizRepository.flush();
		return deleted;
	}

	public Quiz renameQuiz(Integer id, String name) {
		Quiz renamed = quizRepository.getOne(id);
		renamed.setName(name);
		quizRepository.saveAndFlush(renamed);
		return renamed;
	}

	public Question getRandomQuestion(Integer id) {
		Quiz random = quizRepository.getOne(id);
		Integer rand_question = 0;
		if (random.getQuestions().length > 1) {
			Random rand = new Random();
			rand_question = rand.nextInt(random.getQuestions().length);
		}
		Question question = questionService.getQuestion(random.getQuestions()[rand_question]);
		return question;
	}

	public Quiz addQuestion(Integer id, Question q) {
		Quiz added = quizRepository.getOne(id);
		Question question = questionService.addQuestion(new Question(q.getText(), id));
		question.setAnswers(q.getAnswers());
		for (int i = 0; i< question.getAnswers().length; i++) {
			answerService.getAnswer(question.getAnswers()[i]).setQuestion_id(question.getId());
		}
		questionService.QuestionFlush();
		Integer[] newList = new Integer[added.getQuestions().length + 1];
		for (int i = 0; i < added.getQuestions().length; i++)
			newList[i] = added.getQuestions()[i];
		newList[newList.length - 1] = question.getId();
		added.setQuestions(newList);
		quizRepository.saveAndFlush(added);
		return added;
	}

	public Question deleteQuestion(Integer quiz_id, Integer question_id) {
		Quiz deleted = quizRepository.getOne(quiz_id);
		Integer[] newList = new Integer[deleted.getQuestions().length - 1];
		boolean rmd = false;
		for (int i = 0; i < deleted.getQuestions().length; i++) {
			if ((deleted.getQuestions()[i] == question_id) || (deleted.getQuestions()[i] == null)){
				rmd = true;
				continue;
			}
			if (rmd)
				newList[i-1] = deleted.getQuestions()[i];
			else
				newList[i] = deleted.getQuestions()[i];
		}
		deleted.setQuestions(newList);
		quizRepository.saveAndFlush(deleted);
		Question removed = questionService.getQuestion(question_id);
		questionService.deleteQuestion(question_id);
		return removed;
	}

}
