package com.mtate.quizapp.mapper;

import com.mtate.quizapp.dto.AnswerDTO;
import com.mtate.quizapp.dto.QuestionDTO;
import com.mtate.quizapp.entities.Question;
import com.mtate.quizapp.repositories.QuestionRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionMapper {

    private AnswerMapper answerMapper;
    private QuestionRepository questionRepository;


    public QuestionMapper(AnswerMapper answerMapper, QuestionRepository questionRepository) {
        this.answerMapper = answerMapper;
        this.questionRepository = questionRepository;
    }

    public AnswerMapper getAnswerMapper() {
        return this.answerMapper;
    }

    public List<QuestionDTO> entityListTODTOList(Integer[] questions) {
        List<QuestionDTO> result = new ArrayList<QuestionDTO>();
        if ((questions != null)){
            for (int i = 0; i < questions.length; i++) {
                Question q = questionRepository.getOne(questions[i]);
                result.add(entityToDTO(q));

            }
        }
        return result;

    }

    public Integer[] addQuestionList(List<QuestionDTO> q, Integer quiz_id) {
        Integer[] result = new Integer[q.size()];
        if ((q != null) && (!q.isEmpty())) {
            for (int i = 0; i < q.size(); i++) {
                Question temp;
                if (((q.get(i).getAnswers()) != null) && (!q.get(i).getAnswers().isEmpty())) {
                    temp = questionRepository.save(new Question(q.get(i).getText(), quiz_id));
                    temp.setAnswers(getAnswerMapper().addAnswerList(q.get(i).getAnswers(), temp.getId()));
                    questionRepository.flush();
                    result[i] = temp.getId();
                }

            }
        }

        return result;

    }

    public QuestionDTO entityToDTO(Question question) {
        if (question.getAnswers() != null) {
            QuestionDTO result =  new QuestionDTO(question.getId(), question.getText(), new ArrayList<AnswerDTO>(), question.getQuiz_id());
            result.setAnswers(getAnswerMapper().getAnswerDTOList(question.getAnswers()));
            return result;
        }
        else
            return new QuestionDTO(question.getId(), question.getText(), new ArrayList<AnswerDTO>(), question.getQuiz_id());
    }

    public Question dTOTEntity(QuestionDTO question, Integer id) {
        if (question.getAnswers() != null) {
            Question result =  new Question(question.getText(), new Integer[0], id);
            result.setAnswers(getAnswerMapper().addAnswerList(question.getAnswers(), result.getId()));
            return result;
        }
        else
            return new Question(question.getText(), new Integer[0], id);
    }


    public List<QuestionDTO> getQuestionDTOList(Integer[] questions) {
        List<QuestionDTO> result = new ArrayList<QuestionDTO>();
        for (int i = 0; i < questions.length; i++) {
            Question q = questionRepository.getOne(questions[i]);
            result.add(new QuestionDTO(q.getId(), q.getText(), getAnswerMapper().getAnswerDTOList(q.getAnswers()), q.getQuiz_id()));
        }
        return result;
    }



}
