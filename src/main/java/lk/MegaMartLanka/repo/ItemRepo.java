package lk.MegaMartLanka.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.MegaMartLanka.entity.Item;



@Repository
public interface ItemRepo extends JpaRepository<Item,Long>{
     
}
