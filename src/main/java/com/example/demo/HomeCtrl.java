package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/updateCust/{id}")
    public String updateCust(@PathVariable("id") long id, Model model){
        model.addAttribute("customer", customerRepository.findById(id));
        return "addCustomer";
    }

    @RequestMapping("/deleteCust/{id}")
    public String deleteCust(@PathVariable("id") long id){
        customerRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/listSales")
    public String listSales(Model model) {
        model.addAttribute("sales", saleRepository.findAll());
        return "listSales";
    }

    @RequestMapping("/addSales")
    public String addSales(Model model){
        model.addAttribute("sale", new Sale());
        //model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("customers", customerRepository.findAllByIdGreaterThanOrderByName(0));
        return "addSales";
    }

    @PostMapping("/processSale")
    public String processSale(@ModelAttribute Sale sale,
                              @RequestParam("custid") long custid) {
        Customer customer = customerRepository.findById(custid).get();
        sale.setCustomer(customer);
        saleRepository.save(sale);
        return "redirect:/";
    }

    @RequestMapping("/updateSales/{id}")
    public String updateSales(@PathVariable("id") long id, Model model){
        Sale sale = saleRepository.findById(id).get();
        model.addAttribute("sale", sale);
        model.addAttribute("customers", customerRepository.findAllByIdGreaterThanOrderByName(0));
        return "addSales";
    }

    @RequestMapping("/deleteSales/{id}")
    public String deleteSales(@PathVariable("id") long id){
        saleRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/detailSales/{id}")
    public String detailSales(@PathVariable("id") long id, Model model){
        Sale sale = saleRepository.findById(id).get();
        model.addAttribute("sale", sale);

        return "detailSales";
    }
}
