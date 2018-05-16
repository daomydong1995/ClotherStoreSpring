package m07.controller;

import m07.entity.Category;
import m07.entity.ModelReponse;
import m07.entity.Product;
import m07.entity.Supplier;
import m07.repository.CategoryRepository;
import m07.repository.OrderDetailRepository;
import m07.repository.ProductRepository;
import m07.repository.SuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
  private static final int PAGE_SIZE = 5;
  @Autowired
  CategoryRepository categoryRepository;

  @Autowired
  SuppliersRepository suppliersRepository;

  @Autowired
  ProductRepository productRepository;

  @Autowired
  OrderDetailRepository orderDetailRepository;


  @RequestMapping(value = "/user")
  public String admin(Model model) {

    return "admin/user";
  }


  // Hiển thị ListProduct ở trang admin
  @RequestMapping(value = "/listproduct")
  public String listProduct(Model model) {

    return "admin/listproduct";
  }


  @RequestMapping(value = "/tablelist")
  public String tables(Model model) {
    return "admin/tablelist";
  }


  // show check out page
  @RequestMapping(value = "/addcategory")
  public String checkOut(Model model) {
    Category category = new Category();
    model.addAttribute("category", category);
    return "admin/addcategory";
  }


  // thêm category
  @RequestMapping(value = "/addcategory", method = RequestMethod.POST)
  public String addCourse(@Validated @ModelAttribute("category") Category category,
                          ModelMap model,
                          BindingResult bindingResult) {
    Category c = categoryRepository.save(category);
    if (bindingResult.hasErrors()) {

      model.addAttribute("message", "vui lòng");
      return "/admin/addcategory";

    } else {

      model.addAttribute("message", "Chúc mừng");
    }
    return "admin/tablelist";
  }


  /// list category ở table list
  @RequestMapping("/categoryList")
  public @ResponseBody
  ModelReponse showCategory(@RequestParam("pageCa") int pageCa) {
    List<Category> categoryList = (List<Category>) categoryRepository.findAll();
    List<Category> pages = categoryRepository.pageView(pageCa,PAGE_SIZE);
    return new ModelReponse(pages,categoryList.size());
  }

  @RequestMapping("/supplierList")
  public @ResponseBody
  ModelReponse supplierList(@RequestParam("pageSup") int pageSup) {
    List<Supplier> supplierList = (List<Supplier>) suppliersRepository.findAll();
    List<Supplier> pages = suppliersRepository.pageView(pageSup,PAGE_SIZE);
    return new ModelReponse(pages,supplierList.size());
  }

  @RequestMapping("/productList")
  public @ResponseBody
  ModelReponse showproduct(@RequestParam("pagePro") int page) {
    List<Product> productList = (List<Product>) productRepository.listproductdesc();
    List<Product> pages = productRepository.pageView(page,PAGE_SIZE);
    return new ModelReponse(pages,productList.size());
  }

  // Edit category
  @RequestMapping(value = "/editCategory", method = RequestMethod.GET)
  public String editCategory(@RequestParam("id") int id,
                             ModelMap model) {
    model.addAttribute("category1", categoryRepository.findOne(id));
    return "admin/editcategory";
  }

  @RequestMapping(value = "/editCategory", method = RequestMethod.POST)
  public String editStudent(@ModelAttribute("category") Category category, Model model, RedirectAttributes rs) {
    Category cs = categoryRepository.save(category);
    if (null != cs) {
      model.addAttribute("message", "Update success");
      model.addAttribute("category", categoryRepository.findOne(cs.getId()));
    } else {
      model.addAttribute("message", "Update failure");
      model.addAttribute("category", category);
    }
    return "admin/tablelist";
  }

  /// delete Category
  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public String addCourse(@PathVariable("id") int id, ModelMap model) {
    categoryRepository.delete(id);
    return "redirect:/admin/tablelist";
  }


}
