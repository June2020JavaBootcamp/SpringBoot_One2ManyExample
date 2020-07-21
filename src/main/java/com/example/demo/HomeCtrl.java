package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeCtrl {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SaleRepository saleRepository;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("customers", customerRepository.findAll());
        return "index";
    }

    @RequestMapping("/addCustomer")
    public String addCustomer(Model model){
        model.addAttribute("customer", new Customer());
        return "addCustomer";
    }

    @PostMapping("/processCustomer")
    public String processCustomer(@ModelAttribute Customer customer) {
        customerRepository.save(customer);
        return "redirect:/";
    }

    @RequestMapping("/addSales")
    public String addSales(Model model){
        model.addAttribute("sale", new Sale());
        model.addAttribute("customers", customerRepository.findAll());
        return "addSales";
    }

    @PostMapping("/processSale")
    public String processSale(@ModelAttribute Sale sale) {
        saleRepository.save(sale);
        return "redirect:/";
    }


}
