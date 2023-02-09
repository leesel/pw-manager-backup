package com.example.pwmanager.controllers;

import com.example.pwmanager.services.EntryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private EntryItemService entryItemService;

    @GetMapping("/")
    public ModelAndView index (){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("entryItems", entryItemService.getAll());
        return modelAndView;
    }
}
