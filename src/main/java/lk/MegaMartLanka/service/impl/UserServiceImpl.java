package lk.MegaMartLanka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lk.MegaMartLanka.entity.User;
import lk.MegaMartLanka.repo.UserRepo;
import lk.MegaMartLanka.service.UserService;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User create(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userRepository.save(entity);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User update(Long id, User entity) {
        User exUser = userRepository.findById(id).orElse(entity);

        if (exUser == null) {
            return null;
        } else {
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
            exUser.setUsername(entity.getUsername());
            exUser.setPassword(entity.getPassword());
            exUser.setFullname(entity.getFullname());
            exUser.setUserType(entity.getUserType());
            return userRepository.save(exUser);
        }
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
}
