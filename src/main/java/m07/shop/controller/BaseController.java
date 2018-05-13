package m07.shop.controller;

import m07.entity.Category;
import m07.entity.Supplier;
import m07.repository.CategoryRepository;
import m07.repository.SuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class BaseController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SuppliersRepository suppliersRepository;

    @ModelAttribute("categoryList")
    public List<Category> showCategory(Model model) {
        List<Category> categoryList =
                (List<Category>) categoryRepository.findAll();
        return categoryList;
    }

    @ModelAttribute("supplierList")
    public List<Supplier> supplierList1(Model model) {
        List<Supplier> supplierList =
                (List<Supplier>) suppliersRepository.findAll();
        return supplierList;
    }




}
