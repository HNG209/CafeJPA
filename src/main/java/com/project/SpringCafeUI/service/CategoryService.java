package com.project.SpringCafeUI.service;

import com.project.SpringCafeUI.entity.Category;
import com.project.SpringCafeUI.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public boolean isExist(String value){
        return categoryRepository.findByName(value).isEmpty();
    }

    @Transactional
    public boolean addCategory(String value){
        if (isExist(value)){
            categoryRepository.save(new Category(0, value));
            return true;
        }
        return false;
    }

    public Category find(String value){
        return categoryRepository.findByName(value).get(0);
    }
}
