package m07.repository;

import m07.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Repository
public interface CustomersRepository extends JpaRepository<Customer, Integer> {
    @Query(value = " SELECT * FROM customers WHERE id = ? " , nativeQuery = true)
    public Customer findId (String id);
}
