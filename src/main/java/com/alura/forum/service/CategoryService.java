package com.alura.forum.service;

import com.alura.forum.core.crud.CrudService;
import com.alura.forum.model.entity.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends CrudService<Category, Long> {
    @Override
    protected Category editEntity(Category oldEntity, Category entityToSave) {
        oldEntity.setName(entityToSave.getName());
        return oldEntity;
    }
}
