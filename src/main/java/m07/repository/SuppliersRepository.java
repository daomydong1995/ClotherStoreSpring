package m07.repository;


import m07.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public interface SuppliersRepository extends JpaRepository<Supplier, Integer> {
}
