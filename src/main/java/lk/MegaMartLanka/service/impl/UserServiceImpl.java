package lk.MegaMartLanka.service.impl;

import lk.MegaMartLanka.entity.Item;
import lk.MegaMartLanka.entity.User;
import lk.MegaMartLanka.repo.UserRepo;
import lk.MegaMartLanka.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service
@Transactional

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired

    private   PasswordEncoder  passwordEncoder;

    @Override
    public User create(User entity) {
        entity.setPassword (passwordEncoder.encode(entity.getPassword()));
        return userRepository.save (entity);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll ();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername (username).orElse (null);
    }

    @Override
    public User update(Long id, User entity) {
        User exUser = userRepository.findById (id).orElse (entity);


        if (exUser == null) {
            return null;
        } else {
            entity.setPassword (entity.getPassword ());
            exUser.setUsername (entity.getUsername ());
            exUser.setPassword (entity.getPassword ());
            exUser.setFullname (entity.getFullname ());
            exUser.setUserType (entity.getUserType ());
            return userRepository.save (exUser);
        }
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById (id);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById (id).orElse (null);
    }



    //Image upload for User cover

    @SneakyThrows
    @Override
    public int imageUpload(MultipartFile file, long id) {

        String fileName = file.getOriginalFilename ();

        Path uploadPath = Paths.get ("upload/", fileName);

        Files.createDirectories (uploadPath.getParent ());

        Files.write (uploadPath, file.getBytes ());

        String fileUrl = "http://localhost:8080/upload/" + fileName;

        User user = userRepository.findById (id).orElseThrow (() -> new RuntimeException ("User   not found with ID: " + id));

        user.setCoverImgPath (fileUrl);

        User saveCoverImage = userRepository.save (user);

        return 1;
    }

    @Override
    public byte[] getImage(long id) throws IOException {
        User user = userRepository.findById (id).orElseThrow (() -> new RuntimeException ("User  not found with id: " + id));

        String imgUrl = user.getCoverImgPath ();
        System.out.println ("image url :" + imgUrl);

        String fileName = imgUrl.substring (imgUrl.lastIndexOf ("/") + 1);
        System.out.println ("file name :" + fileName);

        Path imgPath = Paths.get ("upload/", fileName);
        System.out.println ("image path :" + imgPath);

        if (!Files.exists (imgPath)) {
            try {
                throw new FileNotFoundException ("Image not found for Item  id: " + id);
            } catch (FileNotFoundException e) {
                throw new RuntimeException (e);
            }
        }

        return Files.readAllBytes (imgPath);
    }



    //Image upload for User Profile

    @SneakyThrows
    @Override
    public int imageUploadProfile(MultipartFile file, long id) {

        String fileName = file.getOriginalFilename ();

        Path uploadPath = Paths.get ("upload/", fileName);

        Files.createDirectories (uploadPath.getParent ());

        Files.write (uploadPath, file.getBytes ());

        String fileUrl = "http://localhost:8080/upload/" + fileName;

        User user = userRepository.findById (id).orElseThrow (() -> new RuntimeException ("User   not found with ID: " + id));

        user.setProfileImgPath (fileUrl);

        User saveProfileImage = userRepository.save (user);

        return 1;
    }

    @Override
    public byte[] getImageProfile(long id) throws IOException {
        User user = userRepository.findById (id).orElseThrow (() -> new RuntimeException ("User  not found with id: " + id));

        String imgUrl = user.getCoverImgPath ();
        System.out.println ("image url :" + imgUrl);

        String fileName = imgUrl.substring (imgUrl.lastIndexOf ("/") + 1);
        System.out.println ("file name :" + fileName);

        Path imgPath = Paths.get ("upload/", fileName);
        System.out.println ("image path :" + imgPath);

        if (!Files.exists (imgPath)) {
            try {
                throw new FileNotFoundException ("Image not found for Item  id: " + id);
            } catch (FileNotFoundException e) {
                throw new RuntimeException (e);
            }
        }

        return Files.readAllBytes (imgPath);
    }

}
