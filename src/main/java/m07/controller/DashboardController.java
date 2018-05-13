package m07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class DashboardController {

    @RequestMapping(value = "/admin/dashboard")
    public String thongke(Model model) {
        return "admin/dashboard";
    }
}
