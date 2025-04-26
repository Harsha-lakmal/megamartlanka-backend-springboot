package lk.MegaMartLanka.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.MegaMartLanka.entity.Order;



@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
    
}
