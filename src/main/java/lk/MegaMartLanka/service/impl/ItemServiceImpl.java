package lk.MegaMartLanka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.MegaMartLanka.entity.ItemEntity;
import lk.MegaMartLanka.repo.ItemRepo;
import lk.MegaMartLanka.service.ItemService;



@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepo itemRepository;

    @Override
    public ItemEntity createItem(ItemEntity entity) {
       return itemRepository.save(entity);
    }

    @Override
    public List<ItemEntity> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public ItemEntity getById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public ItemEntity update(Long id,ItemEntity entity) {
        ItemEntity existingItem = getById(id);

        if(existingItem == null){
            return null;
        } else{
            existingItem.setName(entity.getName());
            existingItem.setDescription(entity.getDescription());
            existingItem.setPrice(entity.getPrice());
            existingItem.setCategory(entity.getCategory());

            return itemRepository.save(existingItem);
        }
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }
    
}
