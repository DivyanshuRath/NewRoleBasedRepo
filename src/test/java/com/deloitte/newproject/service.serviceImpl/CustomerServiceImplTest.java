package com.deloitte.newproject.service.serviceImpl;

import com.deloitte.newproject.model.Customer;
import com.deloitte.newproject.repository.CustomerRepository;
import com.deloitte.newproject.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.deloitte.newproject.util.CustomerTestUtil.createCustomer;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    CustomerRepository customerRepository;


    @Test
    void saveCustomer() {

        Customer customer = createCustomer(1L,"name");
        Mockito.when(customerRepository.save(any(Customer.class)))
                .thenReturn(customer);
        Customer customer1 = customerService.saveCustomer(customer);
        Assertions.assertNotNull(customer1);
    }

    @Test
    void updateCustomer() {
        Customer customer = createCustomer(1L,"name");
        Mockito.when(customerRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(customer));
        Mockito.when(customerRepository.save(any())).thenReturn(customer);
        Customer customer1 = customerService.updateCustomer(1L,customer);

        Assertions.assertEquals(customer.getCustomerName(),customer1.getCustomerName());
    }

    @Test
    void deleteCustomerById() {
        Customer customer = createCustomer(1L,"name");
        Mockito.when(customerRepository.findById(Mockito.anyLong()))
        .thenReturn(Optional.of(customer));
        Mockito.doNothing()
                .when(customerRepository).deleteById(Mockito.any(Long.class));

        customerService.deleteCustomerById(1L);

        verify(customerRepository, times(1)).deleteById(1L);

    }

    @Test
    void getCustomer() {

        List<Customer> customerList = new ArrayList<>();
        Customer customer1 = createCustomer(1L,"name1");
        Customer customer2 = createCustomer(2L,"name2");
        customerList.add(customer1);
        customerList.add(customer2);

        Mockito.when(customerRepository.findAll()).thenReturn(customerList);

        List<Customer> customer = customerService.getCustomer();

        Assertions.assertEquals(customerList.size(),customer.size());

    }

    @Test
    void getCustomerById() {
        Customer customer = createCustomer(1L,"name");

        Mockito.when(customerRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(customer));

        Customer customer1 = customerService.getCustomerById(1L);

        Assertions.assertEquals(customer.getCustomerName(),customer1.getCustomerName());
    }

    @Test
    void updateCustomerFailure() {
            Customer customer = createCustomer(1L,"name");
            customerService.saveCustomer(customer);
            ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class,
        () -> customerService.updateCustomer(2L,customer));

        assertEquals("Customer Not Found", resourceNotFoundException.getMessage());
        }

    @Test
    void getCustomerByIdFailure() {
            ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class,
        () -> customerService.getCustomerById(anyLong()));

        assertEquals("Customer Not Found", resourceNotFoundException.getMessage());
        }

    @Test
    void deleteCustomerByIdFailure() {
            ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class,
        () -> customerService.deleteCustomerById(anyLong()));

        assertEquals("Customer Not Found", resourceNotFoundException.getMessage());
        }
}