package lk.MegaMartLanka.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.MegaMartLanka.entity.OrderEntity;

@Service
public interface OrderService {
    List<OrderEntity> getAll();
    OrderEntity create(OrderEntity entity);
}
