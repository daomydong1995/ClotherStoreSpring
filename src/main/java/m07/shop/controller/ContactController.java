package m07.shop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.internet.MimeMessage;

@Controller
@RequestMapping(value = "/")
public class ContactController {


    //showcontact
    @RequestMapping(value = "/contact")
    public  String showcontact(Model model){

        return "contact";
    }



}
