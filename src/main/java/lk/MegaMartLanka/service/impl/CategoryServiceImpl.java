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

}
