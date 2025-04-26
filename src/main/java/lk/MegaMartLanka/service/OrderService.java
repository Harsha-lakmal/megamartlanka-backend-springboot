package lk.MegaMartLanka.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.MegaMartLanka.entity.Order;

@Service
public interface OrderService {
    List<Order> getAll();
    Order create(Order entity);
}
