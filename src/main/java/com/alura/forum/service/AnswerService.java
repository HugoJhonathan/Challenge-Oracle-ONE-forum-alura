package com.alura.forum.service;

import com.alura.forum.core.crud.CrudService;
import com.alura.forum.model.entity.Answer;
import com.alura.forum.model.entity.Topic;
import com.alura.forum.model.entity.User;
import com.alura.forum.repository.AnswerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Answer answer = getReferenceByIdIfExist(answerId);
        Topic topic = answer.getTopic();
        if (!topic.getUser().equals(user)) throw new AccessDeniedException("You do not have access to this resource!");
        topic.setAnswerSolution(answer);
    }

    @Override
    public Answer getReferenceByIdIfExist(Long id) {
        if (!existsById(id)) throw new EntityNotFoundException("Answer with id " + id + " not exist!");
        return getReferenceById(id);
    }

}
