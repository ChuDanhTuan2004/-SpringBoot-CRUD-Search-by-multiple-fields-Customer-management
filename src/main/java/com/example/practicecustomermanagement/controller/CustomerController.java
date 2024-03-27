package com.example.practicecustomermanagement.controller;

import com.example.practicecustomermanagement.model.Customer;
import com.example.practicecustomermanagement.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @GetMapping("")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("customers", customerService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView addCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("message", "Added new customer successfully!");
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/update");
        modelAndView.addObject("customer", customerService.findById(id).get());
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/update");
        modelAndView.addObject("message", "Update successfully!");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCUstomer(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("redirect:/customers");
        customerService.remove(id);
        return modelAndView;
    }
}
