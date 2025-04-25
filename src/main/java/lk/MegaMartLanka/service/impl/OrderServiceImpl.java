package lk.MegaMartLanka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.MegaMartLanka.entity.OrderEntity;
import lk.MegaMartLanka.repo.OrderRepo;
import lk.MegaMartLanka.service.OrderService;



@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderRepo orderRepository;

    @Override
    public List<OrderEntity> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrderEntity create(OrderEntity entity) {
        return orderRepository.save(entity);
    }
    
}
