package m07.controller;

import m07.entity.Product;
import m07.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class SearchController {


    @Autowired
    ProductRepository productRepository;

//    @RequestMapping(value = "/header")
//    public String showMap() {
//        return "header";
//    }


//    @RequestMapping(value = "/header", method = RequestMethod.GET)
//    @ResponseBody
//    public List<Product> getBooks(@RequestParam String name) {
//        List<Product> result = new ArrayList<>();
//        for(Product product : result) {
//            if (product.getName().contains(name)) {
//                result.add(product);
//            }
//        }
//        return result;
//
//    }

}
