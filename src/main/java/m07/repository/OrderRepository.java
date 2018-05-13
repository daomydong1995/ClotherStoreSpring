package m07.repository;

import m07.entity.Order;
import m07.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    //List sản phẩm đã order by custommer ID
    @Query(value = "select *from orders where customerId = ?", nativeQuery = true)
    public List<Order> listoderbycus (String customerId);


}
