package com.example.practicecustomermanagement.controller;

import com.example.practicecustomermanagement.model.Customer;
import com.example.practicecustomermanagement.service.ICustomerService;
import com.example.practicecustomermanagement.specification.CustomerRequest;
import com.example.practicecustomermanagement.specification.PaginateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @GetMapping("")
    public ModelAndView findAll(@PageableDefault(value = 3) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/list");
        Page<Customer> customers = customerService.findAll(pageable);
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView addCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("message", "Added new customer successfully!");
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/update");
        modelAndView.addObject("customer", customerService.findById(id).get());
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/update");
        modelAndView.addObject("message", "Update successfully!");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCUstomer(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/customers");
        customerService.remove(id);
        return modelAndView;
    }

    @GetMapping("/find")
    public ModelAndView searchCustomerByName(@RequestParam("firstName") String firstName,
                                             @RequestParam("lastName") String lastName,
                                             @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                             @RequestParam(name = "size", required = false, defaultValue = "3") int size) {
        if (firstName.isEmpty()) {
            firstName = null;
        }
        if (lastName.isEmpty()) {
            lastName = null;
        }
        ModelAndView modelAndView = new ModelAndView("/list");
        Page<Customer> customers = customerService.findName(new PaginateRequest(page, size), new CustomerRequest(firstName, lastName));
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
}
