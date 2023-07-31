package com.example.customerinformationsystem.controller;

import com.example.customerinformationsystem.entity.Customer;
import com.example.customerinformationsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public Customer saveCustomer(
            @RequestBody Customer customer
    ) {
        return customerService.saveCustomer(customer);
    }

    @PostMapping("saveAll")
    public String saveCustomers(@RequestBody List<Customer> customers) {
        customerService.saveCustomers(customers);
        return "Customers saved successfully!";
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("{id}")
    public Optional<Customer> getCustomerById(
            @PathVariable("id") Long id
    ) {
        return customerService.getCustomerById(id);
    }


    @GetMapping("names/{name}")
    public List<Customer> getCustomersByName(
            @PathVariable("name") String firstName
    ){
        return customerService.getCustomersByName(firstName);
    }


    @DeleteMapping("{id}")
    public String deleteCustomerById(
            @PathVariable("id") Long id
    ) {
        return customerService.deleteCustomerById(id);
    }

    @PutMapping("update/{id}")
    public Customer updateCustomerById(
            @PathVariable("id") Long id,
            @RequestBody Customer customer
    ){
        return customerService.updateCustomerById(id, customer);
    }
}
