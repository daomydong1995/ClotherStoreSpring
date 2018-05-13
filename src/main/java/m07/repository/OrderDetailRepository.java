package m07.repository;

import m07.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    @Query(value = "SELECT * FROM orderdetails limit 2" , nativeQuery = true)
    public List<OrderDetail> toporder();


    /// thống kê theo san pham
    @Query(value = "SELECT p.name ,  \n" +
            "SUM(o.quantity) as quantity ,    \n" +
            "SUM(o.quantity * o.total_price) as sum, \n" +
            "AVG(o.total_price) as avg  , \n" +
            "Min(o.total_price) as min  , \n" +
            "max(o.total_price) as max \n" +
            "FROM orderdetails o\n" +
            "INNER JOIN products p ON o.productId = p.id\n" +
            "GROUP BY p.name;", nativeQuery = true)

    public List<Object[]> repo();


    /// thong ke san pham theo danh muc
    @Query(value = "SELECT c.name ,  \n" +
            "SUM(o.quantity) as quantity ,    \n" +
            "SUM(o.quantity * o.total_price) as sum, \n" +
            "AVG(o.total_price) as avg  , \n" +
            "Min(o.total_price) as min  , \n" +
            "max(o.total_price) as max \n" +
            "FROM orderdetails o\n" +
            "INNER JOIN products p ON o.productId = p.id\n" +
            "INNER JOIN categories c ON p.categoryId = c.id\n" +
            "GROUP BY c.name;\n", nativeQuery = true)

    public List<Object[]> repowherecategory();


    /// thong ke san pham theo hãng // nhà cung cấp
    @Query(value = "SELECT s.name ,  \n" +
            "SUM(o.quantity) as quantity ,    \n" +
            "SUM(o.quantity * o.total_price) as sum, \n" +
            "AVG(o.total_price) as avg  , \n" +
            "Min(o.total_price) as min  , \n" +
            "max(o.total_price) as max \n" +
            "FROM orderdetails o\n" +
            "INNER JOIN products p ON o.productId = p.id\n" +
            "INNER JOIN suppliers s ON p.supplierId = s.id\n" +
            "GROUP BY s.name;\n", nativeQuery = true)

    public List<Object[]> repowheresuppliers();


//// thống kê sản phẩm theo người đặt hàng
    /// end task 21/03/2018
    @Query(value = " \tSELECT c.id,  \n" +
            "            SUM(o.quantity) as quantity,    \n" +
            "            SUM(o.quantity * o.total_price) as sum, \n" +
            "\t\t\tAVG(o.total_price) as avg, \n" +
            "            Min(o.total_price) as min, \n" +
            "            max(o.total_price) as max \n" +
            "            FROM orderdetails o\n" +
            "            INNER JOIN orders p ON o.orderId = p.id\n" +
            "            INNER JOIN customers c ON p.customerId = c.id\n" +
            "            GROUP BY c.id;", nativeQuery = true)

    public List<Object[]> reportCustommer();


    // thong ke san pham theo năm // theo các năm
    @Query(value = "Select YEAR(od.orderDate) , \n" +
            "SUM(o.quantity) as quantity ,    \n" +
            "SUM(o.quantity * o.total_price) as sum, \n" +
            "AVG(o.total_price) as avg  , \n" +
            "Min(o.total_price) as min  , \n" +
            "max(o.total_price) as max \n" +
            "FROM orderdetails o\n" +
            "INNER JOIN orders od ON o.orderId =od.id\n" +
            "GROUP BY YEAR(od.orderDate);\n", nativeQuery = true)

    public List<Object[]> repowhereyear();


    // thong ke san pham theo tháng // theo các Tháng
    @Query(value = "Select month(od.orderDate) , \n" +
            "SUM(o.quantity) as quantity ,    \n" +
            "SUM(o.quantity * o.total_price) as sum, \n" +
            "AVG(o.total_price) as avg  , \n" +
            "Min(o.total_price) as min  , \n" +
            "max(o.total_price) as max \n" +
            "FROM orderdetails o\n" +
            "INNER JOIN orders od ON o.orderId =od.id\n" +
            "GROUP BY month(od.orderDate);", nativeQuery = true)

    public List<Object[]> repowheremonth();

    // thong ke san pham theo quý // theo các quý
    @Query(value = "    Select QUARTER(od.orderDate), \n" +
            "\tSUM(o.quantity) as quantity ,    \n" +
            "\tSUM(o.quantity * o.total_price) as sum, \n" +
            "\tAVG(o.total_price) as avg  , \n" +
            "\tMin(o.total_price) as min  , \n" +
            "\tmax(o.total_price) as max \n" +
            "\tFROM orderdetails o\n" +
            "\tINNER JOIN orders od ON o.orderId =od.id\n" +
            "\tGROUP By QUARTER(od.orderDate);", nativeQuery = true)

    public List<Object[]> repowhereQUARTER();



    // thong ke san đã ,mua theo id customer
    @Query(value = "SELECT DISTINCT productId , p.customerId , pr.name , pr.image , pr.unitBrief \n" +
            "FROM orderdetails o\n" +
            "INNER JOIN orders p ON o.orderId = p.id\n" +
            "INNER JOIN products pr ON o.productId = pr.id\n" +
            "WHERE p.customerId = ?", nativeQuery = true)

    public List<Object[]> productwherecustomer(String customerId);




    //Tìm kiểm don dat hang
    @Query(value = "select *from orderdetails where orderId = ?", nativeQuery = true)
    public List<OrderDetail> searchOrder(int id);



    //Tìm kiểm sản phẩm
    @Query(value = "SELECT  productId, SUM(quantity) as quantity\n" +
            "FROM orderdetails \n" +
            "group by productId\n" +
            "order by quantity desc", nativeQuery = true)
    public List<OrderDetail> sanphamdathang();


    //Tìm kiểm sản phẩm
    @Query(value = "SELECT  p.name ,  p.image, o.productId, SUM(o.quantity) as quantity , p.unitPrice\n" +
            "FROM orderdetails o\n" +
            " INNER JOIN products p ON o.productId = p.id\n" +
            "group by productId\n" +
            "order by quantity desc\n" +
            "limit 4", nativeQuery = true)
    public List<Object[]> topdathangnhieu();


    //Thông báo khi đặt h
    @Query(value = "select * from orderdetails where id  order by id desc limit 5", nativeQuery = true)
    public List<OrderDetail> thongbaodathang();


    //List Sản phẩm by nhà cung cấp
    @Query(value = "select * from orderdetails where id  = ? ", nativeQuery = true)
    public List<OrderDetail> lisorderby (int id);


    /// list order ở bảng report

    @Query(value = "select *from orderdetails ORDER BY id desc\n ", nativeQuery = true)
    public List<OrderDetail> lisorderbydesc ();






}
