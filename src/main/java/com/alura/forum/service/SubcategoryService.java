package com.alura.forum.service;

import com.alura.forum.core.crud.CrudService;
import com.alura.forum.model.entity.Subcategory;
import org.springframework.stereotype.Service;

@Service
public class SubcategoryService extends CrudService<Subcategory, Long> {
    @Override
    protected Subcategory editEntity(Subcategory oldEntity, Subcategory entityToSave) {
        oldEntity.setName(entityToSave.getName());
        return oldEntity;
    }
}
