package m07.shop.controller;

import m07.entity.Order;
import m07.repository.OrderDetailRepository;
import m07.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class OrderController extends  BaseController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

//    @RequestMapping(value = "/listordercus")
//    public  String showlisstcus(Model model){
//        return "listordercus";
//    }

    //product by category
    @RequestMapping(value = "/listordercus")
    public String showorderbycus(Model model, @RequestParam("customerId") String customerId , HttpServletRequest request) {

        HttpSession httpSession = request.getSession();
        Object s = httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        if (s != null) {
            SecurityContextImpl context = (SecurityContextImpl) s;
            String loggedInUser = context.getAuthentication().getName();
            model.addAttribute("id", loggedInUser);
        }

        List<Order> orderList =
                (List<Order>) orderRepository.listoderbycus(customerId);
        model.addAttribute("listordercus", orderList);
        return "listordercus";
    }



    @RequestMapping(value = "/productbycustome")
    public String productbycustomer12(Model model, @RequestParam("customerId") String customerId , HttpServletRequest request) throws SQLException {

        HttpSession httpSession = request.getSession();
        Object s = httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        if (s != null) {
            SecurityContextImpl context = (SecurityContextImpl) s;
            String loggedInUser = context.getAuthentication().getName();
            model.addAttribute("id", loggedInUser);
        }

        List<Object[]> listTestpro =  orderDetailRepository.productwherecustomer(customerId);
//        System.out.println("id = " + listTestpro.get(0)[0] + "productId = " + listTestpro.get(0)[1]);
        model.addAttribute("productbycustomer",listTestpro);
        return "productbycustome";
    }


}
