package lk.MegaMartLanka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lk.MegaMartLanka.dto.ItemDto;
import lk.MegaMartLanka.entity.ItemEntity;
import lk.MegaMartLanka.service.CategoryService;
import lk.MegaMartLanka.service.ItemService;

@RestController
@RequestMapping("api/v1/MegaMartLanka")

public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/items")
    public ResponseEntity<List<ItemEntity>> getAll() {

        List<ItemEntity> items = itemService.getAll();

        return ResponseEntity.status(200).body(items);
    }

    @PostMapping("/items")
    public ResponseEntity<ItemEntity> createItem(@RequestBody ItemDto dto) {
        ItemEntity newItem = new ItemEntity();
        if (categoryService.getById(dto.getCategoryId()) == null) {
            return ResponseEntity.status(404).body(null);
        } else {
            newItem.setName(dto.getName());
            newItem.setDescription(dto.getDescription());
            newItem.setPrice(dto.getPrice());
            newItem.setCategory(categoryService.getById(dto.getCategoryId()));

            ItemEntity createdItem = itemService.createItem(newItem);

            return ResponseEntity.status(201).body(createdItem);
        }
    }

    @PutMapping("items/{id}")
    public ResponseEntity<ItemEntity> update(@PathVariable Long id, @RequestBody ItemDto dto) {
        ItemEntity newItem = new ItemEntity();
        if (categoryService.getById(dto.getCategoryId()) == null) {
            return ResponseEntity.status(404).body(null);
        } else {
            newItem.setName(dto.getName());
            newItem.setDescription(dto.getDescription());
            newItem.setPrice(dto.getPrice());
            newItem.setCategory(categoryService.getById(dto.getCategoryId()));

            ItemEntity updatedItem = itemService.update(id, newItem);

            return ResponseEntity.status(201).body(updatedItem);
        }
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (itemService.getById(id) == null) {
            return ResponseEntity.status(404).body("Item Not Found");
        } else {
            itemService.delete(id);
            return ResponseEntity.status(200).body("Item Deleted");
        }

    }

}
