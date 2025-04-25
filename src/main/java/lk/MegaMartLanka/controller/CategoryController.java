package lk.MegaMartLanka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lk.MegaMartLanka.entity.CategoryEntity;
import lk.MegaMartLanka.service.CategoryService;

@RestController
@RequestMapping("api/v1/MegaMartLanka")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<List<CategoryEntity>> getAll() {
        List<CategoryEntity> categories = categoryService.getAll();

        return ResponseEntity.status(200).body(categories);
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryEntity> createCategory(@RequestBody CategoryEntity entity) {
        CategoryEntity createdCategory = categoryService.createCategory(entity);

        return ResponseEntity.status(201).body(createdCategory);
    }

}
