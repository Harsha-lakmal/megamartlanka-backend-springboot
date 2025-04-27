package lk.MegaMartLanka.controller;

import lk.MegaMartLanka.entity.Category;
import lk.MegaMartLanka.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/MegaMartLanka")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = categoryService.getAll ();

        return ResponseEntity.status (200).body (categories);
    }

    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category entity) {
        Category createdCategory = categoryService.createCategory (entity);

        return ResponseEntity.status (201).body (createdCategory);
    }

    @DeleteMapping("/category/deelete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        String Category = categoryService.deleteCategory (id);
        return ResponseEntity.status (204).body (Category);
    }

    @PutMapping("/update/category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        categoryService.updateCategory (id, category);
        return ResponseEntity.status (204).body (category);
    }

}
