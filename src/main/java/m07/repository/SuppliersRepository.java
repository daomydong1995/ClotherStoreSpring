package m07.repository;


import m07.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Repository
public interface SuppliersRepository extends JpaRepository<Supplier, Integer> {
    @Query(value = "SELECT * FROM Suppliers LIMIT ?,?",nativeQuery = true)
    public List<Supplier> pageView(int index , int limited);
}
