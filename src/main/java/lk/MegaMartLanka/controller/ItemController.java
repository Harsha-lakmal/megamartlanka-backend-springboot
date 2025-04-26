package lk.MegaMartLanka.controller;

import lk.MegaMartLanka.dto.ItemDto;
import lk.MegaMartLanka.entity.Item;
import lk.MegaMartLanka.service.CategoryService;
import lk.MegaMartLanka.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/MegaMartLanka")

public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAll() {

        List<Item> items = itemService.getAll ();

        return ResponseEntity.status (200).body (items);
    }

    @PostMapping("/items")
    public ResponseEntity<Item> createItem(@RequestBody ItemDto dto) {
        Item newItem = new Item ();
        if (categoryService.getById (dto.getCategoryId ()) == null) {
            return ResponseEntity.status (404).body (null);
        } else {
            newItem.setName (dto.getName ());
            newItem.setDescription (dto.getDescription ());
            newItem.setPrice (dto.getPrice ());
            newItem.setCategory (categoryService.getById (dto.getCategoryId ()));

            Item createdItem = itemService.createItem (newItem);

            return ResponseEntity.status (201).body (createdItem);
        }
    }

    @PutMapping("items/{id}")
    public ResponseEntity<Item> update(@PathVariable Long id, @RequestBody ItemDto dto) {
        Item newItem = new Item ();
        if (categoryService.getById (dto.getCategoryId ()) == null) {
            return ResponseEntity.status (404).body (null);
        } else {
            newItem.setName (dto.getName ());
            newItem.setDescription (dto.getDescription ());
            newItem.setPrice (dto.getPrice ());
            newItem.setCategory (categoryService.getById (dto.getCategoryId ()));

            Item updatedItem = itemService.update (id, newItem);

            return ResponseEntity.status (201).body (updatedItem);
        }
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (itemService.getById (id) == null) {
            return ResponseEntity.status (404).body ("Item Not Found");
        } else {
            itemService.delete (id);
            return ResponseEntity.status (200).body ("Item Deleted");
        }

    }

}
