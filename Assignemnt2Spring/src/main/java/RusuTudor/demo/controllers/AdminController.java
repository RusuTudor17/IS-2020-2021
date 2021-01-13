package RusuTudor.demo.controllers;

import RusuTudor.demo.data.RoleRepository;
import RusuTudor.demo.data.UserRepository;
import RusuTudor.demo.models.User;
import RusuTudor.demo.service.RoleService;
import RusuTudor.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("index")
    public String displayAllOptions(Model model){
        return "admin/index";
    }

    @GetMapping("displayEmployees")
    public String displayAllEmployees(Model model){
        model.addAttribute("title", "All Events");
        model.addAttribute("users", userService.findAll());
        return "admin/displayEmployees";
    }

    @GetMapping("createEmployees")
    public String displayCreateEmployee(Model model) {

        model.addAttribute(new User());
        model.addAttribute("userPrimit",new Object());
        model.addAttribute("roles",roleService.findAll());
        return "admin/createEmployees";

    }

    @PostMapping("createEmployees")
    public String processCreateEmployee(@ModelAttribute @Valid User user,Errors errors,
                                        Model model){

        if(model.getAttribute("roles") == null){
            model.addAttribute("roles",roleService.findAll());
        }

        System.out.println("AICI" + errors.getErrorCount());
        if(errors.hasErrors()){
            model.addAttribute("userPrimit",new Object());
            return "admin/createEmployees";
        }
        else {
            if (userService.findUserByGivenEmail(user.getEmail()) == null) {
                model.addAttribute("userPrimit", new Object());
                userService.save(user);
                return "redirect:displayEmployees";
            } else {
                model.addAttribute("userPrimit", null);
                return "admin/createEmployees";
            }
        }
    }


    @GetMapping("deleteEmployee")
    public String displayDeleteEmployee(Model model) {

        model.addAttribute("users",userService.findAll().stream().filter(user -> user.getRol().getRoleName().equals("Employee")).collect(Collectors.toList()));

        return "admin/deleteEmployee";
    }


    @PostMapping("deleteEmployee")
    public String processDeleteEmployee(@RequestParam(required=false) Integer userIds){
        if(userIds!=null){
            userService.deleteById(userIds);
            return "redirect:displayEmployees";
        }
        else{
            return "redirect:deleteEmployee";
        }

    }


    @GetMapping("updateEmployee")
    public String displayUpdateEmployee(Model model) {

        model.addAttribute("users",userService.findAll().stream().filter(user -> user.getRol().getRoleName().equals("Employee")).collect(Collectors.toList()));
        return "admin/updateEmployee";
    }


    @GetMapping("changePassword")
    public String displayChangePassword(@RequestParam Integer userId, Model model) {

        Optional<User> result = userService.findById(userId);
        if(result.isPresent()) {
            System.out.println(result.get().getEmail());

            model.addAttribute(result.get());
        }
        return "admin/changePassword";
    }


    @PostMapping("changePassword")
    public String processChangePassword(@RequestParam Integer userId, @Valid @ModelAttribute User user, Errors errors){


        if(errors.hasErrors()){
            return "admin/changePassword";
        }
        else {

            userService.updatePasswordForGivenEmail(user.getPassword(),userId);
            return "redirect:displayEmployees";
        }
    }


}
