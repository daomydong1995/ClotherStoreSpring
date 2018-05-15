package m07.repository;

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
public interface ProductRepository  extends JpaRepository<Product, Integer> {


    //Hiển thị danh sách product mới nhất  ở trang chủ LIMIT = 6
    @Query(value = " SELECT * FROM products ORDER BY productDate DESC limit 6" , nativeQuery = true)
    public List<Product> listproduct6 ();

    //List Sản phẩm by danh mục
    @Query(value = "select  *from products where idCategory = ? ", nativeQuery = true)
    public List<Product> listproductBycategory (int idCategory);

    //List Sản phẩm by nhà cung cấp
    @Query(value = "select  *from products where idSupplier = ? ", nativeQuery = true)
    public List<Product> listproductBysupper (int idSupplier);



    @Query(value = "select *from products ORDER BY idProduct desc", nativeQuery = true)
    public List<Product> listproductdesc ();


    //Tìm kiểm sản phẩm
    @Query(value = "select *from products where name = ?", nativeQuery = true)
    public List<Product> searchProduct(String name);

    List<Product> findByNameContainingOrCategoryNameContaining(String productName, String categoryName);

    //lọc giá cả
    @Query(value = "SELECT * FROM products WHERE unitPrice >= 1 AND unitPrice <= 5 " , nativeQuery = true)
    public List<Product> filterprice();

    //lọc giá cả
    @Query(value = "SELECT * FROM products WHERE unitPrice >= 5 AND unitPrice <= 10 " , nativeQuery = true)
    public List<Product> filterprice010();

    //lọc giá cả
    @Query(value = "SELECT * FROM products WHERE unitPrice >= 10 AND unitPrice <= 15 " , nativeQuery = true)
    public List<Product> filterprice1015();


    //lọc giá cả
    @Query(value = "SELECT * FROM products WHERE unitPrice >= 15 AND unitPrice <= 20 " , nativeQuery = true)
    public List<Product> filterprice1520();

    //lọc giá cả
    @Query(value = "select  *from  products where unitPrice >= 20 " , nativeQuery = true)
    public List<Product> filterprice20();

    // tìm kiếm sản phẩm sale
    @Query(value = "SELECT * FROM  products where discount = ?" , nativeQuery = true)
    public  List<Product> sale(Double discount);





}
