package com.alura.forum.service;

import com.alura.forum.core.crud.CrudService;
import com.alura.forum.model.entity.Course;
import org.springframework.stereotype.Service;

@Service
public class CourseService extends CrudService<Course, Long> {
    @Override
    protected Course editEntity(Course oldEntity, Course entityToSave) {
        oldEntity.setName(entityToSave.getName());
        return oldEntity;
    }
}
