package lk.MegaMartLanka.controller;

import lk.MegaMartLanka.entity.Item;
import lk.MegaMartLanka.entity.User;
import lk.MegaMartLanka.repo.UserRepo;
import lk.MegaMartLanka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/MegaMartLanka")

public class UserController {

    @Autowired
    private UserRepo  userRepo ;

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll ();

        return ResponseEntity.status (200).body (users);
    }

    @PostMapping("/AddUsers")
    public ResponseEntity<User> create(@RequestBody User entity) {

        User newUser = userService.create (entity);

        return ResponseEntity.status (201).body (newUser);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User entity) {
        User updatedUser = userService.update (id, entity);

        if (updatedUser == null) {
            return ResponseEntity.status (404).body (null);
        } else {
            return ResponseEntity.status (200).body (updatedUser);
        }
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id) {

        if (userService.getById (id) == null) {
            return ResponseEntity.status (404).body ("User Not Found");
        } else {
            userService.delete (id);
            return ResponseEntity.status (200).body ("User Deleted");
        }
    }



    //Image upload for  Cover User


    @PostMapping("/uploadCover/{id}")
    public ResponseEntity<String> imageUpload(@RequestParam("file") MultipartFile file, @PathVariable long id) throws IOException {
        int i = userService.imageUpload (file, id);

        if (i == 1) {
            return new ResponseEntity<String> ("Upload Success !", HttpStatus.CREATED);
        }
        return new ResponseEntity<> ("Upload Failed", HttpStatus.BAD_REQUEST);

    }

    private String getFileExtension(String url) {
        if (url == null || !url.contains (".")) {
            return null;
        }
        return url.substring (url.lastIndexOf (".") + 1);
    }

    private MediaType getMediaTypeForFileExtension(String extension) {
        switch (extension.toLowerCase ()) {
            case "png":
                return MediaType.IMAGE_PNG;
            case "gif":
                return MediaType.IMAGE_GIF;
            case "jpg":
            case "jpeg":
                return MediaType.IMAGE_JPEG;
            case "webp":
                return MediaType.valueOf ("image/webp");
            case "bmp":
                return MediaType.valueOf ("image/bmp");
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    @GetMapping("/get/imageCover/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable long id) throws IOException {

        Optional<User> userOpt = userRepo.findById (id);
        if (!userOpt.isPresent ()) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }

        User user = userOpt.get ();
        String imgUrl = user.getCoverImgPath ();

        String fileExtension = getFileExtension (imgUrl);
        if (fileExtension == null || fileExtension.isEmpty ()) {
            return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
        }

        byte[] image;
        try {
            image = userService.getImage (id);
        } catch (FileNotFoundException e) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
        }

        MediaType mediaType = getMediaTypeForFileExtension (fileExtension);

        HttpHeaders headers = new HttpHeaders ();
        headers.setContentType (mediaType);

        return new ResponseEntity<> (image, headers, HttpStatus.OK);
    }




    //Image upload for  profile   User


    @PostMapping("/uploadProfile/{id}")
    public ResponseEntity<String> imageUploadProfile(@RequestParam("file") MultipartFile file, @PathVariable long id) throws IOException {
        int i = userService.imageUploadProfile(file, id);

        if (i == 1) {
            return new ResponseEntity<String> ("Upload Success !", HttpStatus.CREATED);
        }
        return new ResponseEntity<> ("Upload Failed", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/get/imageProfile/{id}")
    public ResponseEntity<byte[]> getImageProfile(@PathVariable long id) throws IOException {

        Optional<User> userOpt = userRepo.findById (id);
        if (!userOpt.isPresent ()) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }

        User user = userOpt.get ();
        String imgUrl = user.getProfileImgPath ();

        String fileExtension = getFileExtension (imgUrl);
        if (fileExtension == null || fileExtension.isEmpty ()) {
            return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
        }

        byte[] image;
        try {
            image = userService.getImageProfile(id);
        } catch (FileNotFoundException e) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
        }

        MediaType mediaType = getMediaTypeForFileExtension (fileExtension);

        HttpHeaders headers = new HttpHeaders ();
        headers.setContentType (mediaType);

        return new ResponseEntity<> (image, headers, HttpStatus.OK);
    }




}
