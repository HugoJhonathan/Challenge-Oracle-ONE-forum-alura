package com.alura.forum.service;

import com.alura.forum.core.crud.CrudService;
import com.alura.forum.model.entity.Answer;
import com.alura.forum.model.entity.Topic;
import com.alura.forum.model.projections.TopicCompleteDTO;
import com.alura.forum.model.projections.TopicSlimDTO;
import com.alura.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicService extends CrudService<Topic, Long> {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private AnswerService answerService;

    @Override
    protected Topic editEntity(Topic oldEntity, Topic entityToSave) {
        oldEntity.setCategory(entityToSave.getCategory());
        oldEntity.setSubcategory(entityToSave.getSubcategory());
        oldEntity.setCourse(entityToSave.getCourse());
        oldEntity.setMessage(entityToSave.getMessage());
        oldEntity.setTitle(entityToSave.getTitle());
        return oldEntity;
    }

    @Override
    public Topic save(Topic entity) {
        //entity.setCreatedAt(LocalDateTime.now());
        return super.save(entity);
    }

    public List<TopicSlimDTO> findAllSlim() {
        return topicRepository.findAllBy();
    }

    public TopicCompleteDTO findComplete(Long id) {
        return topicRepository.findTopicCompleteById(id)
                .orElseThrow(() -> new RuntimeException("Topic with id " + id + " not found!"));
    }

    public void setAnswerSolution(Long topicId, Long answerId) {
        if (!existsById(topicId)) {
            throw new IllegalArgumentException("Topic with id " + topicId + " not found!");
        }
        if (!answerService.existsById(answerId)) {
            throw new IllegalArgumentException("Answer with id " + answerId + " not found!");
        }
        Topic topic = getReferenceById(topicId);
        Answer answer = answerService.getReferenceById(answerId);
        topic.setAnswerSolution(answer);
    }

    public Answer addAnswerToPost(Answer answer, Long idPost) {
        Topic topic = getReferenceById(idPost);
        answer.setTopic(topic);
        answer.setCreatedAt(LocalDateTime.now());
        return answerService.save(answer);
    }

}
