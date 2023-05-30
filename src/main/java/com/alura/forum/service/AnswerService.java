package com.alura.forum.service;

import com.alura.forum.core.crud.CrudService;
import com.alura.forum.model.entity.Answer;
import com.alura.forum.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService extends CrudService<Answer, Long> {

    @Autowired
    private AnswerRepository repository;

    @Override
    protected Answer editEntity(Answer oldEntity, Answer entityToSave) {
        oldEntity.setMessage(entityToSave.getMessage());
        return oldEntity;
    }

    @Override
    public Answer save(Answer entity) {
//        entity.setCreatedAt(LocalDateTime.now());
        return super.save(entity);
    }

    public List<Answer> findAllByTopicId(Long id) {
        return repository.findAllByTopicId(id);
    }

    public void setAnswerSolution(Long answerId) {
        if (!repository.existsById(answerId)) {
            throw new IllegalArgumentException("Answer with id " + answerId + " not found!");
        }
        Answer answer = repository.getReferenceById(answerId);
        answer.getTopic().setAnswerSolution(answer);
    }

}
