package lk.MegaMartLanka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lk.MegaMartLanka.entity.UserEntity;
import lk.MegaMartLanka.service.UserService;


@RestController
@RequestMapping("api/v1/MegaMartLanka")

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getAll() {
        List<UserEntity> users = userService.getAll();

        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/users")
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity entity) {

        UserEntity newUser = userService.create(entity);

        return ResponseEntity.status(201).body(newUser);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity entity) {
        UserEntity updatedUser = userService.update(id, entity);

        if (updatedUser == null) {
            return ResponseEntity.status(404).body(null);
        } else {
            return ResponseEntity.status(200).body(updatedUser);
        }
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id) {

        if (userService.getById(id) == null) {
            return ResponseEntity.status(404).body("User Not Found");
        } else {
            userService.delete(id);
            return ResponseEntity.status(200).body("User Deleted");
        }
    }

}
