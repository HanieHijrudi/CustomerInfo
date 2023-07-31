package com.example.customerinformationsystem.service;

import com.example.customerinformationsystem.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    List<Customer> getCustomers();

    Optional<Customer> getCustomerById(Long id);

    String deleteCustomerById(Long id);

    void saveCustomers(List<Customer> customers);

    Customer updateCustomerById(Long id, Customer customer);

    List<Customer> getCustomersByName(String firstName);
}
