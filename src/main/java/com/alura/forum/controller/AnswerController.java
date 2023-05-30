package com.alura.forum.controller;

import com.alura.forum.core.crud.CrudController;
import com.alura.forum.model.dto.request.AnswerDTO;
import com.alura.forum.model.dto.response.AnswerResponseDTO;
import com.alura.forum.model.entity.Answer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answers")
public class AnswerController extends CrudController<Answer, Long, AnswerDTO, AnswerResponseDTO> {

    @Override
    public ResponseEntity<String> create(AnswerDTO e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

}
