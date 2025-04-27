package lk.MegaMartLanka.service.impl;

import lk.MegaMartLanka.entity.Order;
import lk.MegaMartLanka.repo.OrderRepo;
import lk.MegaMartLanka.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll ();
    }

    @Override
    public Order create(Order entity) {
        return orderRepository.save (entity);
    }

}
