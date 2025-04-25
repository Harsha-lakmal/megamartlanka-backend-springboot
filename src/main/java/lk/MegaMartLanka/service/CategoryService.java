package lk.MegaMartLanka.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.MegaMartLanka.entity.CategoryEntity;


@Service
public interface CategoryService {
    CategoryEntity createCategory(CategoryEntity categoryEntity);
    List<CategoryEntity> getAll();
    CategoryEntity getById(Long id);
}
