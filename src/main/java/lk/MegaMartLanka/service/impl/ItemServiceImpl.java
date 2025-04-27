package lk.MegaMartLanka.service.impl;

import lk.MegaMartLanka.entity.Item;
import lk.MegaMartLanka.repo.ItemRepo;
import lk.MegaMartLanka.service.ItemService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepo itemRepository;

    @Override
    public Item createItem(Item entity) {
        return itemRepository.save (entity);
    }


    @Override
    public List<Item> getAll() {
        return itemRepository.findAll ();
    }

    @Override
    public Item getById(Long id) {
        return itemRepository.findById (id).orElse (null);
    }

    @Override
    public Item update(Long id, Item entity) {
        Item existingItem = getById (id);

        if (existingItem == null) {
            return null;
        } else {
            existingItem.setName (entity.getName ());
            existingItem.setDescription (entity.getDescription ());
            existingItem.setPrice (entity.getPrice ());
            existingItem.setCategory (entity.getCategory ());

            return itemRepository.save (existingItem);
        }
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById (id);
    }

    //Image upload for item

    @SneakyThrows
    @Override
    public int imageUpload(MultipartFile file, long id) {

        String fileName = file.getOriginalFilename ();

        Path uploadPath = Paths.get ("upload/", fileName);

        Files.createDirectories (uploadPath.getParent ());

        Files.write (uploadPath, file.getBytes ());

        String fileUrl = "http://localhost:8080/upload/" + fileName;

        Item item = itemRepository.findById (id).orElseThrow (() -> new RuntimeException ("Item  not found with ID: " + id));

        item.setImgPath (fileUrl);

        Item saveItemImage = itemRepository.save (item);

        return 1;
    }

    @Override
    public byte[] getImage(long id) throws IOException {
        Item item = itemRepository.findById (id).orElseThrow (() -> new RuntimeException ("Item  not found with id: " + id));

        String imgUrl = item.getImgPath ();
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
