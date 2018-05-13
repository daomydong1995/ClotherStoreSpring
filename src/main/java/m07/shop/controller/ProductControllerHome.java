package m07.shop.controller;

import m07.entity.Product;
import m07.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProductControllerHome extends  BaseController{

    @Autowired
    ProductRepository productRepository;


    //// list product ở trang chủ
    @ModelAttribute("sale")
    public List<Product> sale(Model model,Double discount) {
        List<Product> productList =
                (List<Product>) productRepository.sale(discount);

        return productList;
    }




    @RequestMapping(value = "productfiter")
    public String showproductbyid(Model model){
        List<Product> productList =
                (List<Product>) productRepository.filterprice();
        model.addAttribute("productList",productList);
        return "product";
    }

    @RequestMapping(value = "filterprice010")
    public String filterprice010(Model model){
        List<Product> productList =
                (List<Product>) productRepository.filterprice010();
        model.addAttribute("productList",productList);
        return "product";
    }

    @RequestMapping(value = "filterprice1015")
    public String filterprice1015(Model model){
        List<Product> productList =
                (List<Product>) productRepository.filterprice1015();
        model.addAttribute("productList",productList);
        return "product";
    }

    @RequestMapping(value = "filterprice1520")
    public String filterprice1520(Model model){
        List<Product> productList =
                (List<Product>) productRepository.filterprice1520();
        model.addAttribute("productList",productList);
        return "product";
    }


    @RequestMapping(value = "filterprice20")
    public String filterprice20(Model model){
        List<Product> productList =
                (List<Product>) productRepository.filterprice20();
        model.addAttribute("productList",productList);
        return "product";
    }


}
