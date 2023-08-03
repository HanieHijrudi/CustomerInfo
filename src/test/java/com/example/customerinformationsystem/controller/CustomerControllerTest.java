package com.example.customerinformationsystem.controller;

import com.example.customerinformationsystem.entity.Customer;
import com.example.customerinformationsystem.error.CustomerDetailsInvalidException;
import com.example.customerinformationsystem.error.CustomerNotFoundException;
import com.example.customerinformationsystem.error.NoCustomersToSaveException;
import com.example.customerinformationsystem.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@WebMvcTest(CustomerControllerTest.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private Customer customer1;
    private Customer customer2;

    @BeforeEach
    public void setUp() {
        customer1 = Customer.builder()
                .id(1L)
                .firstName("Meisam")
                .lastName("Heydari")
                .email("meisam@gmail.ccom")
                .userName("meisam")
                .password("1234567890")
                .build();
        customer2 = Customer.builder()
                .id(2L)
                .firstName("Ali")
                .lastName("Maleki")
                .email("maleki@gmail.ccom")
                .userName("maleki")
                .password("maleki")
                .build();
    }

    @Test
    public void testSaveCustomer_ValidCustomer() throws CustomerDetailsInvalidException {

        when(customerService.saveCustomer(customer1)).thenReturn(customer1);

        Customer savedCustomer = customerController.saveCustomer(customer1);

        verify(customerService).saveCustomer(customer1);

        assertEquals(customer1, savedCustomer);
    }

    @Test
    public void testSaveCustomer_InvalidCustomer() throws CustomerDetailsInvalidException {

        when(customerService.saveCustomer(customer1)).thenThrow(CustomerDetailsInvalidException.class);

        assertThrows(CustomerDetailsInvalidException.class, () -> {
            customerController.saveCustomer(customer1);
        });

        verify(customerService).saveCustomer(customer1);
    }

    @Test
    public void testSaveCustomers_ValidCustomers() throws NoCustomersToSaveException {
        List<Customer> validCustomers = new ArrayList<>();
        validCustomers.add(customer1);
        validCustomers.add(customer2);

        customerController.saveCustomers(validCustomers);

        verify(customerService).saveCustomers(validCustomers);
    }

    @Test
    public void testSaveCustomers_NoCustomersToSave() {
        List<Customer> emptyCustomers = new ArrayList<>();

        doThrow(NoCustomersToSaveException.class).when(customerService).saveCustomers(emptyCustomers);

        try {
            customerController.saveCustomers(emptyCustomers);
        } catch (NoCustomersToSaveException e) {
            assertEquals(NoCustomersToSaveException.class, e.getClass());
        }
    }

    @Test
    public void testGetCustomers_ValidCustomers() throws CustomerDetailsInvalidException {
        List<Customer> validCustomers = new ArrayList<>();
        validCustomers.add(customer1);
        validCustomers.add(customer2);

        when(customerService.getCustomers()).thenReturn(validCustomers);

        List<Customer> fetchCustomers = customerController.getCustomers();

        verify(customerService).getCustomers();

        assertEquals(validCustomers, fetchCustomers);
    }

    @Test
    public void testGetCustomers_CustomerNotFoundException() {

        when(customerService.getCustomers()).thenThrow(CustomerNotFoundException.class);

        assertThrows(CustomerNotFoundException.class, () -> customerController.getCustomers());

        verify(customerService).getCustomers();
    }

    @Test
    public void testGetCustomersByName_ValidCustomers() throws CustomerNotFoundException {
        List<Customer> validCustomers = new ArrayList<>();
        validCustomers.add(customer1);
        validCustomers.add(customer2);

        when(customerService.getCustomersByName("Meisam")).thenReturn(validCustomers);

        List<Customer> fetchedCustomers = customerController.getCustomersByName("Meisam");

        verify(customerService).getCustomersByName("Meisam");

        assertEquals(validCustomers, fetchedCustomers);
    }

    @Test
    public void testGetCustomersByName_CustomerNitFoundException() {

        when(customerService.getCustomersByName("invalidName")).thenThrow(CustomerNotFoundException.class);

        assertThrows(CustomerNotFoundException.class, () -> customerController.getCustomersByName("invalidName"));

        verify(customerService).getCustomersByName("invalidName");
    }

    @Test
    public void testGetCustomersById_ValidCustomer() throws CustomerNotFoundException {

        when(customerService.getCustomerById(1L)).thenReturn(Optional.ofNullable(customer1));

        Optional<Customer> fetchedCustomer = customerController.getCustomerById(1L);

        verify(customerService).getCustomerById(1L);

        assertEquals(Optional.ofNullable(customer1), fetchedCustomer);

    }

    @Test
    public void testGetCustomerById_CustomerNotFoundException() throws CustomerNotFoundException {

        when(customerService.getCustomerById(99L)).thenThrow(CustomerNotFoundException.class);

        assertThrows(CustomerNotFoundException.class, () -> customerController.getCustomerById(99L));

        verify(customerService).getCustomerById(99L);
    }

    @Test
    public void testDeleteCustomerById_ValidCustomer() throws CustomerNotFoundException {
        when(customerService.deleteCustomerById(1L)).thenReturn("Customer with ID 1 deleted successfully.");

        String deleteMessage = customerController.deleteCustomerById(1L);

        verify(customerService).deleteCustomerById(1L);

        assertEquals("Customer with ID 1 deleted successfully.", deleteMessage);
    }

    @Test
    public void testDeleteCustomerById_CustomerNotFoundException() throws CustomerNotFoundException {

        when(customerService.deleteCustomerById(1L)).thenThrow(CustomerNotFoundException.class);

        assertThrows(CustomerNotFoundException.class, () -> customerController.deleteCustomerById(1L));

        verify(customerService).deleteCustomerById(1L);
    }

    @Test
    public void testUpdateCustomerById_ValidCustomer() throws CustomerNotFoundException {

        when(customerService.updateCustomerById(1L, customer1)).thenReturn(customer1);

        Customer updateCustomer = customerController.updateCustomerById(1L, customer1);

        verify(customerService).updateCustomerById(1L, customer1);

        assertEquals(customer1, updateCustomer);
    }

    @Test
    public void testUpdateCustomerById_CustomerNotFoundException() throws CustomerNotFoundException {

        when(customerService.updateCustomerById(1L, customer1)).thenThrow(CustomerNotFoundException.class);

        assertThrows(CustomerNotFoundException.class, () -> customerController.updateCustomerById(1L, customer1));

        verify(customerService).updateCustomerById(1L, customer1);
    }


}