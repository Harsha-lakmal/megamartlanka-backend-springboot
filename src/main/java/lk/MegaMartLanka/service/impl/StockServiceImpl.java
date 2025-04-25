package lk.MegaMartLanka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.MegaMartLanka.entity.ItemEntity;
import lk.MegaMartLanka.entity.StockEntity;
import lk.MegaMartLanka.repo.StockRepo;
import lk.MegaMartLanka.service.StockService;



@Service
public class StockServiceImpl implements StockService{

    @Autowired
    private StockRepo stockRepository;

    @Override
    public StockEntity create(StockEntity entity) {
        StockEntity news = stockRepository.save(entity);
        return news;
    }

    @Override
    public List<StockEntity> getAll() {
        return stockRepository.findAll();
    }

    @Override
    public StockEntity addToStock(Long id, Long qty) {

        StockEntity exStok = stockRepository.findById(id).orElse(null);

        if (exStok == null) {
            return null;
        } else {
            exStok.setQoh(exStok.getQoh()+qty);
            return stockRepository.save(exStok);
        }
        
    }

    @Override
    public StockEntity getFromStock(Long id, Long qty) {

        StockEntity exStok = stockRepository.findById(id).orElse(null);

        if (exStok == null) {
            return null;
        } else {
            exStok.setQoh(exStok.getQoh()-qty);
            return stockRepository.save(exStok);
        }

    }

    @Override
    public StockEntity getByItem(ItemEntity entity) {
        return stockRepository.findByItem(entity).orElse(null);
    }

    @Override
    public StockEntity updateStock(Long id, Long qty) {
        
        StockEntity exStok = stockRepository.findById(id).orElse(null);

        if ( exStok == null) {
            return null;
        } else {
            exStok.setQoh(qty);
            return stockRepository.save(exStok);
        }
    }
    
}
