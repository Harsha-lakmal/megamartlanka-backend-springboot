package lk.MegaMartLanka.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.MegaMartLanka.entity.Item;



@Service
public interface ItemService {
    Item createItem(Item entity);
    List<Item> getAll();
    Item getById(Long id);
    Item update(Long id, Item entity);
    void delete(Long id);
}
