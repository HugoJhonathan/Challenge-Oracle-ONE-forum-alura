package com.alura.forum.model.projections;

import com.alura.forum.model.entity.enums.AnswerStatus;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.Instant;

@JsonPropertyOrder({"id", "title", "createdAt", "status", "statusName", "category", "subcategory", "course", "author"})
public interface TopicSlimDTO {
    Long getId();

    String getTitle();

    Instant getCreatedAt();

    Integer getStatus();

    default String getStatusName() {
        return AnswerStatus.findById(getStatus()).getName();
    }

    CategorySlim getCategory();

    SubcategorySlim getSubcategory();

    CourseSlim getCourse();

    UserSlim getAuthor();

}