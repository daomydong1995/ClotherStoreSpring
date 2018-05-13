package m07.shop.controller;


import m07.entity.OrderDetail;
import m07.entity.Product;
import m07.repository.OrderDetailRepository;
import m07.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/")
public class HomeController extends BaseController {



    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    private static final int dataPerPage = 6; // moi trang co 7 data
    private static int totalPages = 0;

    @RequestMapping(method = RequestMethod.GET)
    public String showProduct(Model model, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Object s = httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        if (s != null) {
            SecurityContextImpl context = (SecurityContextImpl) s;
            String loggedInUser = context.getAuthentication().getName();
            model.addAttribute("id", loggedInUser);
        }
        return "home";
    }



    /// list product ở trang chủ
    @ModelAttribute("listproduct6")
    public List<Product> listproduct6(Model model) {
        List<Product> productList =
                (List<Product>) productRepository.listproduct6();

        return productList;
    }



    @ModelAttribute("producttop")
    public String reporttop(Model model) throws SQLException {
        OrderDetail orderDetail = new OrderDetail();
        model.addAttribute("orderDetail", orderDetail);
        List<Object[]> listTest1 =  orderDetailRepository.topdathangnhieu();
        System.out.println("id = " + listTest1.get(0)[0] + "productId = " + listTest1.get(0)[1]);
        model.addAttribute("listTest1",listTest1);
        return "producttop";
    }


    @RequestMapping(value = "detail")
    public String showProductDetail(
            @RequestParam("id") String id,
            Model model) {
        Product product =
                productRepository.findOne(Integer.valueOf(id));
        model.addAttribute("product", product);
        return "detail";
    }


    // show trang product
//    @RequestMapping(value = "product")
//    public String showproduct(Model model) {
//        List<Product> productList =
//                (List<Product>) productRepository.findAll();
//        model.addAttribute("productList", productList);
//        return "product";
//    }


    //product by category
    @RequestMapping(value = "productbycategory")
    public String showproductbyid(Model model, @RequestParam("categoryId") int categoryId) {
        List<Product> productList =
                (List<Product>) productRepository.listproductBycategory(categoryId);
        model.addAttribute("productList", productList);
        return "product";
    }


    // product by supline
    @RequestMapping(value = "productbysupplier")
    public String showproductbysup(Model model, @RequestParam("supplierId") int supplierId) {
        List<Product> productList =
                (List<Product>) productRepository.listproductBysupper(supplierId);
        model.addAttribute("productList", productList);
        return "product";
    }

    // show check out page
    @RequestMapping(value = "/header")
    public String showseachr(Model model, @RequestParam("name") String name) {
        model.addAttribute("product", productRepository.searchProduct(name));
        return "header";
    }


    @RequestMapping(value = "/header" , method = RequestMethod.POST)
    public String timkiem(Model model, @RequestParam("name") String search) {
        // c1
        List<Product> productList =
                (List<Product>) productRepository.findByNameContainingOrCategoryNameContaining(search, search);

//        // c2
//        List<Product> productList2 =
//                (List<Product>) productRepository.findAll();
//        List<Product> result = new ArrayList<>();
//        for(Product product : productList2) {
//            if (product.getName().contains(search)
//                    || product.getCategory().getName().contains(search)) {
//                result.add(product);
//            }
//        }

        model.addAttribute("productList", productList);
        return "product";
    }


    @RequestMapping(method = GET , value = "/product")
    public String home(Model model,
                       @RequestParam(value = "page", required = false, defaultValue = "1") String page) {
        int selectedPage = Integer.valueOf(page);
        model.addAttribute("productList", getProductsPerPage(Integer.valueOf(selectedPage)));
        model.addAttribute("startPage", selectedPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("visiblePages", totalPages > 3 ? (totalPages / 2) : totalPages);
        return "product";
    }

    private List<Product> getProductsPerPage(int page){
        List<Product> productList = (List<Product>) productRepository.findAll();
        totalPages = (int) Math.ceil((double) productList.size() / dataPerPage);

        int start = (page - 1) * dataPerPage;
        int end = start + dataPerPage;
        if (end >= productList.size()) {
            end = productList.size();
        }
        List<Product> productsPerPage = new ArrayList<>();
        for (int i = start; i < end; i++) {
            productsPerPage.add(productList.get(i));
        }
        return productsPerPage;
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public String showProducts(Model model, HttpServletRequest request) {
//        HttpSession httpSession = request.getSession();
//        Object s = httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
//        if (s != null) {
//            SecurityContextImpl context = (SecurityContextImpl) s;
//            String loggedInUser = context.getAuthentication().getName();
//            model.addAttribute("id", loggedInUser);
//
//
//        }
//        return "home";
//
//    }

}



