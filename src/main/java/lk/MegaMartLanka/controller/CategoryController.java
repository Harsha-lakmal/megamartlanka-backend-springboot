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

}
