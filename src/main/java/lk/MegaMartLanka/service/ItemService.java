package lk.MegaMartLanka.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import lk.MegaMartLanka.entity.Item;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface ItemService {
    Item createItem(Item entity);
    List<Item> getAll();
    Item getById(Long id);
    Item update(Long id, Item entity);
    void delete(Long id);

    int imageUpload(MultipartFile file, long id);

    byte[] getImage(long id) throws IOException;
}
