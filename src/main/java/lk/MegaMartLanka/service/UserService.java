package lk.MegaMartLanka.service;

import lk.MegaMartLanka.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface UserService {
    User create(User entity);

    List<User> getAll();

    User getByUsername(String username);

    User update(Long id, User entity);

    void delete(Long id);

    User getById(Long id);

    byte[] getImage(long id) throws IOException;

    int imageUpload(MultipartFile file, long id);

    byte[] getImageProfile(long id) throws IOException;

    int imageUploadProfile(MultipartFile file, long id);
}
