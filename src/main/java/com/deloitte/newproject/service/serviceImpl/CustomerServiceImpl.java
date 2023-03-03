package com.deloitte.newproject.service.serviceImpl;

import com.deloitte.newproject.model.Customer;
import com.deloitte.newproject.repository.CustomerRepository;
import com.deloitte.newproject.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.newproject.exception.ResourceNotFoundException;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * This is a service class which is a component that contains the business logic of the application.
 * It typically acts as a middle layer between the controller and the repository, handling the processing of data and applying the necessary business rules before passing the data to the repository for storage or retrieval.
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    CustomerRepository customerRepository;

    /**
     * The RestTemplate is a class provided by Spring Framework that simplifies communication with HTTP servers and provides convenient methods to perform HTTP requests.
     * Usage tip: MyObject myObject = restTemplate.getForObject(url, MyObject.class);
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * This method handles business logic to save resource into DB
     * @param customer The resource to be saved
     * @return The added resource
     */
    @Override
    @CachePut(cacheNames = "Customer", key = "#customer.customerId")
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * This method handles business logic to update the resource found in DB by its id
     * @param id The id of the resource
     * @param customer The new resource body
     * @return The updated resource
     */
    @Override
    @CachePut(cacheNames = "Customer", key = "#id")
    public Customer updateCustomer(Long id, Customer customer) {
        Customer customerObj = customerRepository.findById(id)
                                       .orElseThrow(() -> new ResourceNotFoundException("Customer Not Found"));
        customerObj.setCustomerName(customer.getCustomerName());
        return customerRepository.save(customerObj);
    }

    /**
     * This method handles business logic to delete the resource found in DB by its id
     * @param id The id of the resource
     */
    @Override
    @CacheEvict(cacheNames = "Customer", key = "#id")
    public void deleteCustomerById(Long id) {
        customerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer Not Found"));
        customerRepository.deleteById(id);
    }

    /**
     * This method handles business logic to retrieve all the resources found in DB
     * @return The resources list
     */
    @Override
    public List<Customer>getCustomer() {
        return customerRepository.findAll();
    }

    /**
     * This method handles business logic to get a specific resource found in DB by its id
     * @param id The id of the resource
     * @return The found resource
     */
    @Override
    @Cacheable(cacheNames = "Customer", key = "#id")
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer Not Found"));
    }
}