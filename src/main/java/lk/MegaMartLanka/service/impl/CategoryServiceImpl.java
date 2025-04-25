package lk.MegaMartLanka.service.impl;

import java.util.List;

import lk.MegaMartLanka.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.MegaMartLanka.entity.CategoryEntity;
import lk.MegaMartLanka.service.CategoryService;



@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepository;

    @Override
    public CategoryEntity createCategory(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public List<CategoryEntity> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity getById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
    
}
