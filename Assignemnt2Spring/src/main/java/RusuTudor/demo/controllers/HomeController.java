package RusuTudor.demo.controllers;

import RusuTudor.demo.data.UserRepository;
import RusuTudor.demo.models.User;
import RusuTudor.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class HomeController {


    @Autowired
    private UserService userService;



    @GetMapping
    public String viewLogin(Model model){
        model.addAttribute("title","Login");
        model.addAttribute(new User());
        return "index";
    }


    @PostMapping
    public String processLogin(@RequestParam String email, @RequestParam String password, Model model){
        Integer user = userService.findUserByGivenEmailAndPassword(email,password);
        System.out.println("sunt 2");
        if(user != null)
        {
            System.out.println("AICI TATA: "+userService.findById(user).get().getRol().getRoleName());
            Optional<User> result= userService.findById(user);
            if (result.get().getRol().getRoleName().equals("Admin")) {
                System.out.println("sunt aici");
                model.addAttribute("userType", "regular_user");
                return "redirect:/admin/index";
            }
            else {
                System.out.println("sunt aiciewqewqewq");
                model.addAttribute("myUser", "regular_user");
                return "redirect:/employee/index";
            }


        }else{
            return "redirect:";
        }

    }
}
