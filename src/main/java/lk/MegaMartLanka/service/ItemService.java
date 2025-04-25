package lk.MegaMartLanka.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.MegaMartLanka.entity.ItemEntity;



@Service
public interface ItemService {
    ItemEntity createItem(ItemEntity entity);
    List<ItemEntity> getAll();
    ItemEntity getById(Long id);
    ItemEntity update(Long id,ItemEntity entity);
    void delete(Long id);
}
