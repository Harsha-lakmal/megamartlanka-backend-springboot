package lk.MegaMartLanka.service.impl;

import lk.MegaMartLanka.entity.Item;
import lk.MegaMartLanka.repo.ItemRepo;
import lk.MegaMartLanka.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
