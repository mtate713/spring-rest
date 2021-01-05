package com.mtate.quizapp.mapper;

import com.mtate.quizapp.dto.AnswerDTO;
import com.mtate.quizapp.entities.Answer;
import com.mtate.quizapp.repositories.AnswerRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnswerMapper {

    private AnswerRepository answerRepository;

    public AnswerMapper(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }


    public List<AnswerDTO> entityListTODTOList(Integer[] answers) {
        List<AnswerDTO> result = new ArrayList<AnswerDTO>();
        if (answers.length != 0) {
            for (int i = 0; i< answers.length; i++) {
                Answer a = answerRepository.getOne(answers[i]);
                result.add(new AnswerDTO(a.getText(), a.isIs_correct(), a.getQuestion_id()));

            }
        }
        return result;
    }

    public Integer[] addAnswerList(List<AnswerDTO> a, Integer question_id) {
        Integer[] result = new Integer[a.size()];
        if ((a != null)) {
            Answer temp;
            for (int i = 0; i < a.size(); i++) {
                temp = answerRepository.save(new Answer(a.get(i).getText(), a.get(i).isIs_Correct(), question_id));
                answerRepository.flush();
                result[i] = temp.getId();
            }
        }
        return result;

    }

    public List<AnswerDTO> getAnswerDTOList(Integer[] answers) {
        List<AnswerDTO> result = new ArrayList<AnswerDTO>();
        if (answers != null) {
            for (int i = 0; i < answers.length; i++) {
                Answer a = answerRepository.getOne(answers[i]);
                result.add(new AnswerDTO(a.getId(), a.getText(), a.isIs_correct(), a.getQuestion_id()));
            }
        }
        return result;
    }



}
