package com.mtate.quizapp.mapper;

import com.mtate.quizapp.dto.QuestionDTO;
import com.mtate.quizapp.dto.QuizDTO;
import com.mtate.quizapp.entities.Question;
import com.mtate.quizapp.entities.Quiz;
import com.mtate.quizapp.repositories.QuizRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class QuizMapper {

    private QuestionMapper questionMapper;

    public QuizMapper(QuestionMapper questionMapper){
        this.questionMapper= questionMapper;
    }

    public QuestionMapper getQuestionMapper() {
        return this.questionMapper;
    }

    public List<QuizDTO> entityListToDTOList(List<Quiz> quizzes) {
        List<QuizDTO> result = new ArrayList<QuizDTO>();
        if ((quizzes != null) && (!quizzes.isEmpty())) {
            for (Quiz q : quizzes) {
                result.add(entityToDTO(q));
            }
        }
        return result;
    }

    public QuizDTO entityToDTO(Quiz quiz) {
        if ((quiz.getQuestions() != null))
            return new QuizDTO(quiz.getId(), quiz.getName(), getQuestionMapper().getQuestionDTOList(quiz.getQuestions()));
        else
            return new QuizDTO(quiz.getId(), quiz.getName(), new ArrayList<QuestionDTO>());
    }

    public Quiz dTOToEntity(QuizDTO quiz) {
        if ((quiz.getQuestions() != null))
            return new Quiz(quiz.getName(), getQuestionMapper().addQuestionList(quiz.getQuestions(), quiz.getId()));
        else
            return new Quiz(quiz.getName(), new Integer[0]);
    }
}
