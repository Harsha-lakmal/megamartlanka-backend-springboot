package lk.MegaMartLanka.service.impl;

import lk.MegaMartLanka.entity.Category;
import lk.MegaMartLanka.repo.CategoryRepo;
import lk.MegaMartLanka.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepository;

    @Override
    public Category createCategory(Category categoryEntity) {
        return categoryRepository.save (categoryEntity);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll ();
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById (id).orElse (null);
    }

    @Override
    public String deleteCategory(Long id) {
        if (categoryRepository.existsById (id)) {
            categoryRepository.deleteById (id);
            return "Category deleted";
        } else {
            return "Category not found";

        }

    }

    @Override
    public String updateCategory(Long id, Category category) {
        if (categoryRepository.existsById (id)) {
            Category categoryEntity = categoryRepository.findById (id).orElse (null);
            categoryEntity.setName (category.getName());
             categoryRepository.save(categoryEntity);
             return "Update to Category";

        }else {
            return "Category not found";
        }

    }

}
