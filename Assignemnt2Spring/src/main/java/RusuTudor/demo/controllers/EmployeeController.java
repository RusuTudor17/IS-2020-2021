package RusuTudor.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    @GetMapping("index")
    public String displayAllOptions(Model model){
        return "employee/index";
    }
}
