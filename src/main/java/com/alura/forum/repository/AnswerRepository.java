package com.alura.forum.repository;

import com.alura.forum.core.crud.CrudRepository;
import com.alura.forum.model.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {

    Page<Answer> findAllByTopicId(Long id, Pageable pageable);
}
