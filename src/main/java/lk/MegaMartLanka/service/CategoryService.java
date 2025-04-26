package lk.MegaMartLanka.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.MegaMartLanka.entity.Category;


@Service
public interface CategoryService {
    Category createCategory(Category categoryEntity);
    List<Category> getAll();
    Category getById(Long id);
}
