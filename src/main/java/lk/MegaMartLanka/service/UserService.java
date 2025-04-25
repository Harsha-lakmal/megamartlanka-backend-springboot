package lk.MegaMartLanka.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.MegaMartLanka.entity.UserEntity;

@Service
public interface UserService {
    UserEntity create(UserEntity entity);
    List<UserEntity> getAll();
    UserEntity getByUsername(String username);
    UserEntity update(Long id, UserEntity entity);
    void delete(Long id);
    UserEntity getById(Long id);
}
