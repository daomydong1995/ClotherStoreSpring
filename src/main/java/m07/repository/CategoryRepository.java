package m07.repository;

import m07.entity.Category;
import m07.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "SELECT * FROM Categories LIMIT ?,?",nativeQuery = true)
    public List<Category> pageView(int index , int limited);
}

