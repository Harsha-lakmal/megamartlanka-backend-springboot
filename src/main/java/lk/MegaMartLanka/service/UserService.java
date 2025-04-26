package lk.MegaMartLanka.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.MegaMartLanka.entity.User;

@Service
public interface UserService {
    User create(User entity);
    List<User> getAll();
    User getByUsername(String username);
    User update(Long id, User entity);
    void delete(Long id);
    User getById(Long id);
}
