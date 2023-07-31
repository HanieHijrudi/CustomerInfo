package com.example.customerinformationsystem.service;

import com.example.customerinformationsystem.ProgressTracker;
import com.example.customerinformationsystem.entity.Customer;
import com.example.customerinformationsystem.error.CustomerDetailsInvalidException;
import com.example.customerinformationsystem.error.CustomerNotFoundException;
import com.example.customerinformationsystem.error.NoCustomersToSaveException;
import com.example.customerinformationsystem.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Logger LOGGER =
            LoggerFactory.getLogger(CustomerService.class);


    @Autowired
    private ProgressTracker progressTracker;


    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void saveCustomers(List<Customer> customers) {
        int totalCustomers = customers.size();

        if(totalCustomers == 0){
            throw new NoCustomersToSaveException("No customers to save!!!");
        }

        progressTracker.setTotalCustomers(totalCustomers);

        for (Customer customer : customers) {
            customerRepository.save(customer);

            progressTracker.incrementProgress();

            int progressPercentage = progressTracker.getProgressPercentage();
            int currentProgress = progressTracker.getCurrentProgress();
            LOGGER.info("Saving customer: {} of {} ({}% complete)", currentProgress, totalCustomers, progressPercentage);
        }
    }


    /*    @Autowired
        public void CustomerService(ProgressTracker progressTracker, CustomerRepository customerRepository) {
            this.progressTracker = progressTracker;
            this.customerRepository = customerRepository;


        @Override
        public void saveCustomers(List<Customer> customers) {
            int totalCustomers = customers.size();
            progressTracker.setTotalCustomers(totalCustomers);

            for (Customer customer : customers){
                customerRepository.save(customer);

                progressTracker.incrementProgress();

                int currentProgress = progressTracker.getProgressPercentage();
                int currentCustomer = progressTracker.getCurrentProgress();
                LOGGER.info("Saving customer: {} of {} ({}% complete)", currentCustomer, totalCustomers, currentProgress);
            }
        } */
    @Override
    public Customer saveCustomer(Customer customer) {

        if(customer==null || customer.getEmail()==null || customer.getPassword()==null || customer.getFirstName()==null||
            customer.getLastName()==null || customer.getUserName()==null){
            throw new CustomerDetailsInvalidException(("Customer details must not be empty"));
        }

        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomers()
            throws CustomerNotFoundException{
        List<Customer> customers = customerRepository.findAll();

        if(customers.isEmpty()){
            throw new CustomerNotFoundException("No customers found in the database.");
        }
        return customers;
    }

    @Override
    public Optional<Customer> getCustomerById(Long id){
        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isEmpty()){
            throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        }
        return customer;
//        return customer.orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found"));
    }

    @Override
    public String deleteCustomerById(Long id) {
        if (!customerRepository.existsById(id)){
            throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        }
        customerRepository.deleteById(id);
        return "Customer with ID " + id + " deleted successfully!";
    }


    @Override
    public Customer updateCustomerById(Long id, Customer customer) {
        Optional<Customer> findCustomer = customerRepository.findById(id);
        if (!findCustomer.isPresent()) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        }
        customer.setId(id);
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomersByName(String firstName) {
        List<Customer> customers = customerRepository.findByFirstNameIgnoreCase(firstName);

        if(customers.isEmpty()){
            throw new CustomerNotFoundException(("No customers found with name: " + firstName));
        }
        return customers;
    }
}
