package com.example.customerinformationsystem.service;

import com.example.customerinformationsystem.ProgressTracker;
import com.example.customerinformationsystem.entity.Customer;
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
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public String deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
        return "Customer information deleted successfully!";
    }


    @Override
    public Customer updateCustomerById(Long id, Customer customer) {
        Optional<Customer> findCustomer = customerRepository.findById(id);
        if (!findCustomer.isPresent()) {
            throw new RuntimeException();
        }
        customer.setId(id);
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomersByName(String firstName) {
        return customerRepository.findByFirstNameIgnoreCase(firstName);
    }
}
