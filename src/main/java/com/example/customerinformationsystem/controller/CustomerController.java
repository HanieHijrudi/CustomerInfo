package com.example.customerinformationsystem.controller;

import com.example.customerinformationsystem.entity.Customer;
import com.example.customerinformationsystem.error.CustomerDetailsInvalidException;
import com.example.customerinformationsystem.error.CustomerNotFoundException;
import com.example.customerinformationsystem.error.NoCustomersToSaveException;
import com.example.customerinformationsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public Customer saveCustomer(
            @RequestBody Customer customer)
            throws CustomerDetailsInvalidException {
        return customerService.saveCustomer(customer);
    }

    @PostMapping("saveAll")
    public String saveCustomers(
            @RequestBody List<Customer> customers)
            throws NoCustomersToSaveException{
        customerService.saveCustomers(customers);
        return "Customers saved successfully!";
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers()
            throws CustomerNotFoundException{
        return customerService.getCustomers();
    }


    @GetMapping("names/{name}")
    public List<Customer> getCustomersByName(
            @PathVariable("name") String firstName)
            throws CustomerNotFoundException{
        return customerService.getCustomersByName(firstName);
    }


    @GetMapping("{id}")
    public Optional<Customer> getCustomerById(
            @PathVariable("id") Long id)
            throws CustomerNotFoundException {
        return customerService.getCustomerById(id);
    }


    @DeleteMapping("{id}")
    public String deleteCustomerById(
            @PathVariable("id") Long id)
            throws CustomerNotFoundException {
        return customerService.deleteCustomerById(id);
    }

    @PutMapping("update/{id}")
    public Customer updateCustomerById(
            @PathVariable("id") Long id,
            @RequestBody Customer customer)
            throws CustomerNotFoundException {
        return customerService.updateCustomerById(id, customer);
    }
}
