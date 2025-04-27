package lk.MegaMartLanka.controller;

import lk.MegaMartLanka.dto.ItemDto;
import lk.MegaMartLanka.entity.Item;
import lk.MegaMartLanka.repo.ItemRepo;
import lk.MegaMartLanka.service.CategoryService;
import lk.MegaMartLanka.service.ItemService;
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

public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ItemRepo itemRepo;

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

    //Image upload for  item


    @PostMapping("/upload/{id}")
    public ResponseEntity<String> imageUpload(@RequestParam("file") MultipartFile file, @PathVariable long id) throws IOException {
        int i = itemService.imageUpload (file, id);

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

    @GetMapping("/get/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable long id) throws IOException {

        Optional<Item> courseOpt = itemRepo.findById (id);
        if (!courseOpt.isPresent ()) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }

        Item item = courseOpt.get ();
        String imgUrl = item.getImgPath ();

        String fileExtension = getFileExtension (imgUrl);
        if (fileExtension == null || fileExtension.isEmpty ()) {
            return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
        }

        byte[] image;
        try {
            image = itemService.getImage (id);
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
