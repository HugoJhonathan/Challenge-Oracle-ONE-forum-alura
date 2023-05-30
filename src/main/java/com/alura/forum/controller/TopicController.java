package com.alura.forum.controller;

import com.alura.forum.converter.AnswerConverter;
import com.alura.forum.core.crud.CrudController;
import com.alura.forum.model.dto.request.AnswerDTO;
import com.alura.forum.model.dto.request.TopicDTO;
import com.alura.forum.model.dto.response.TopicResponseDTO;
import com.alura.forum.model.entity.Answer;
import com.alura.forum.model.entity.Topic;
import com.alura.forum.model.projections.TopicCompleteDTO;
import com.alura.forum.model.projections.TopicSlimDTO;
import com.alura.forum.service.AnswerService;
import com.alura.forum.service.TopicService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
@AllArgsConstructor
public class TopicController extends CrudController<Topic, Long, TopicDTO, TopicResponseDTO> {

    private TopicService topicService;
    private AnswerService answerService;
    private AnswerConverter answerConverter;

    @Override
    public ResponseEntity<List<TopicSlimDTO>> findAll() {
        return ResponseEntity.ok(topicService.findAllSlim());
    }

    @Override
    public ResponseEntity<TopicCompleteDTO> findById(@PathVariable("id") Long aLong) {
        return ResponseEntity.ok(topicService.findComplete(aLong));
    }

    @GetMapping("/{id}/answers")
    public ResponseEntity<List<Answer>> findAnswersByTopic(@PathVariable("id") Long id) {
        return ResponseEntity.ok(answerService.findAllByTopicId(id));
    }

    @Transactional
    @PostMapping("/{id}/answers")
    public ResponseEntity<Answer> addAnswerToPost(@PathVariable("id") Long id, @RequestBody @Valid AnswerDTO answerDTO) {
        Answer answer = answerConverter.dtoCadToEntity(answerDTO);
        Answer answer1 = topicService.addAnswerToPost(answer, id);
        return ResponseEntity.ok(answer1);
    }

    @Transactional
    @PostMapping("/{topicId}/answer_solution/{answerId}")
    public ResponseEntity<String> answerSolution(@PathVariable("topicId") Long topicId, @PathVariable("answerId") Long answerId) {
        topicService.setAnswerSolution(topicId, answerId);
        return ResponseEntity.ok().build();
    }

}
