package com.mtate.quizapp.controller;

import java.util.List;

import com.mtate.quizapp.dto.QuestionDTO;
import com.mtate.quizapp.dto.QuizDTO;
import com.mtate.quizapp.mapper.QuizMapper;
import org.springframework.web.bind.annotation.*;

import com.mtate.quizapp.services.QuizService;

@RestController
public class QuizController {


	private QuizService quizService;

	private QuizMapper quizMapper;

	

	public QuizController(QuizService quizService, QuizMapper quizMapper) {

		this.quizService = quizService;
		this.quizMapper = quizMapper;
	}

	public QuizMapper getQuizMapper() {
		return this.quizMapper;
	}

	@GetMapping("/quiz")
	public List<QuizDTO> getQuizzes() {
		return getQuizMapper().entityListToDTOList(quizService.getQuizzes());

	}
	
	@PostMapping("/quiz")
	public QuizDTO addQuiz(@RequestBody QuizDTO quiz) {
		return this.getQuizMapper().entityToDTO(quizService.addQuiz(this.getQuizMapper().dTOToEntity(quiz)));
		
	}
	
	@DeleteMapping("/quiz/{id}")
	public QuizDTO deleteQuiz(@PathVariable("id")Integer id) {
		return this.getQuizMapper().entityToDTO(quizService.deleteQuiz(id));
		
	}
	
	@PatchMapping("/quiz/{id}/rename/{newName}")
	public QuizDTO renameQuiz(@PathVariable("id")Integer id, @PathVariable("newName")String name) {
		return this.getQuizMapper().entityToDTO(quizService.renameQuiz(id, name));
		
	}
	
	@GetMapping("/quiz/{id}/random")
	public QuestionDTO getRandomQuestion(@PathVariable("id") Integer id) {
		return this.getQuizMapper().getQuestionMapper().entityToDTO(quizService.getRandomQuestion(id));
		
	}
	
	@PatchMapping("/quiz/{id}/add")
	public QuizDTO addQuestion(@PathVariable("id") Integer id, @RequestBody QuestionDTO q) {
		return this.getQuizMapper().entityToDTO(quizService.addQuestion(id, this.getQuizMapper().getQuestionMapper().dTOTEntity(q, id)));
		
	}
	
	@DeleteMapping("/quiz/{id}/delete/{questionID}") 
	public QuestionDTO deleteQuestion(@PathVariable("id")Integer quiz_id, @PathVariable("questionID")Integer question_id) {
		return this.getQuizMapper().getQuestionMapper().entityToDTO(quizService.deleteQuestion(quiz_id, question_id));
		
	}
}
