package org.launchcode.hellospring.controllers;


import org.launchcode.hellospring.data.EventCategoryRepository;
import org.launchcode.hellospring.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryControllers {




    @Autowired
    private EventCategoryRepository eventCategoryRepository;





    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("title","All Categories");
        model.addAttribute("eventCategories", eventCategoryRepository.findAll());
        return "eventCategories/index";
    }





    @GetMapping("create")
    public String renderCreateEventForm(Model model){
        model.addAttribute("title","Create Category");
        model.addAttribute(new EventCategory());
        return "eventCategories/create";
    }






    @PostMapping("create")
    public String createEvent(@ModelAttribute @Valid EventCategory newEventCategory,
                              Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title","Create Category");
            return "eventCategories/create";
        }
        eventCategoryRepository.save(newEventCategory);
        return "redirect:";
    }

}
