package lk.MegaMartLanka.service;

import lk.MegaMartLanka.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CategoryService {
    Category createCategory(Category categoryEntity);

    List<Category> getAll();

    Category getById(Long id);

    String deleteCategory(Long id);


    String updateCategory(Long id, Category category);
}
