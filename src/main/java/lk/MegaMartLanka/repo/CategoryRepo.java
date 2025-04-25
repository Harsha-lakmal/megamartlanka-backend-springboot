package lk.MegaMartLanka.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.MegaMartLanka.entity.CategoryEntity;



@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity,Long>{
    
}
