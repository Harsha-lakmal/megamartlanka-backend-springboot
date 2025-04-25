package lk.MegaMartLanka.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.MegaMartLanka.entity.ItemEntity;



@Repository
public interface ItemRepo extends JpaRepository<ItemEntity,Long>{
     
}
