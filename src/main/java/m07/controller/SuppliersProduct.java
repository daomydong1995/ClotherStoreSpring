package m07.controller;

import m07.entity.Category;
import m07.entity.Product;
import m07.entity.Supplier;
import m07.repository.SuppliersRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class SuppliersProduct {


    @Autowired
    SuppliersRepository suppliersRepository;


    @RequestMapping(value = "/admin/addsuppliers")
    public String addproduct(Model model) {
        Supplier supplier = new Supplier();
        model.addAttribute("supplier", supplier);
        return "admin/addsuppliers";
    }


    // thêm category
    @RequestMapping(value = "/admin/addsuppliers", method = RequestMethod.POST)
    public String addCourse(@ModelAttribute("category") Supplier supplier, ModelMap model,
                            @RequestParam("file") MultipartFile file
            , HttpServletRequest httpServletRequest) {

        String path = httpServletRequest.getSession().getServletContext().getRealPath("/") + "resources/uploads/";

        try {
            FileUtils.forceMkdir(new File(path));
            File upload = new File(path + file.getOriginalFilename());
            file.transferTo(upload);
        } catch (IOException e) {
            e.printStackTrace();
        }

        supplier.setLogo(file.getOriginalFilename());
        Supplier s = suppliersRepository.save(supplier);

        if (null != s) {
            model.addAttribute("message", "Update success");
            model.addAttribute("category", supplier);
        } else {
            model.addAttribute("message", "Update failure");
            model.addAttribute("supplier", supplier);
        }
        return "/admin/tablelist";
    }


    // Edit category
    @RequestMapping(value = "/admin/editsuppliers", method = RequestMethod.GET)
    public String editSupper(@RequestParam("id") int id,
                             ModelMap model) {
        model.addAttribute("supplier1", suppliersRepository.findOne(id));
        return "admin/editsuppliers";
    }


    @RequestMapping(value = "/admin/editsuppliers", method = RequestMethod.POST)
    public String editSupp(@ModelAttribute("supplier") Supplier supplier, Model model,
                           @RequestParam("file") MultipartFile file
            , HttpServletRequest httpServletRequest) {


        String path = httpServletRequest.getSession().getServletContext().getRealPath("/") + "resources/uploads/";


        try {
            FileUtils.forceMkdir(new File(path));
            File upload = new File(path + file.getOriginalFilename());
            file.transferTo(upload);
        } catch (IOException e) {
            e.printStackTrace();
        }

        supplier.setLogo(file.getOriginalFilename());

        Supplier cs = suppliersRepository.save(supplier);
        if (null != cs) {
            model.addAttribute("message", "Update success");
            model.addAttribute("category", suppliersRepository.findOne(cs.getId()));
        } else {
            model.addAttribute("message", "Update failure");
            model.addAttribute("supplier", supplier);
        }
        return "admin/tablelist";
    }

    ///delete Category
    @RequestMapping(value = "deletesup/{id}", method = RequestMethod.GET)
    public String deletesup(@PathVariable("id") int id, ModelMap model) {
        suppliersRepository.delete(id);
        return "redirect:/admin/tablelist";
    }

}
