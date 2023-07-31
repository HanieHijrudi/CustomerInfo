package com.example.customerinformationsystem.service;

import com.example.customerinformationsystem.entity.Customer;
import com.example.customerinformationsystem.error.CustomerDetailsInvalidException;
import com.example.customerinformationsystem.error.CustomerNotFoundException;
import com.example.customerinformationsystem.error.NoCustomersToSaveException;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer saveCustomer(Customer customer)throws CustomerDetailsInvalidException;

    List<Customer> getCustomers() throws CustomerNotFoundException;

    Optional<Customer> getCustomerById(Long id) throws CustomerNotFoundException;

    String deleteCustomerById(Long id) throws CustomerNotFoundException;

    void saveCustomers(List<Customer> customers) throws NoCustomersToSaveException;

    Customer updateCustomerById(Long id, Customer customer) throws CustomerNotFoundException;

    List<Customer> getCustomersByName(String firstName) throws CustomerNotFoundException;
}
