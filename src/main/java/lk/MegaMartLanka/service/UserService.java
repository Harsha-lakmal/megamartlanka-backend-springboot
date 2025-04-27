package lk.MegaMartLanka.service;

import lk.MegaMartLanka.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User create(User entity);

    List<User> getAll();

    User getByUsername(String username);

    User update(Long id, User entity);

    void delete(Long id);

    User getById(Long id);
}
