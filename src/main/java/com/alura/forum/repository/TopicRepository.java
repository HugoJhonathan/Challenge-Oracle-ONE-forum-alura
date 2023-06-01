package com.alura.forum.repository;

import com.alura.forum.core.crud.CrudRepository;
import com.alura.forum.model.entity.Topic;
import com.alura.forum.model.projections.TopicCompleteDTO;
import com.alura.forum.model.projections.TopicSlimDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {

    List<TopicSlimDTO> findAllBy();

    Optional<TopicCompleteDTO> findTopicCompleteById(Long aLong);

    List<TopicSlimDTO> findAllTopicSlimByCategoryId(Long id);

    List<TopicSlimDTO> findAllByUser(UserDetails user);

    @Override
    Optional<Topic> findById(Long aLong);

}

