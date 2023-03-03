package com.deloitte.newproject.service;

import com.deloitte.newproject.model.Customer;
import java.util.List;

/**
 * This is the service layer interface which declares all methods that implementation class should implement
 */
public interface CustomerService {

    /**
     * Save resource method declaration
     */
    Customer saveCustomer(Customer customer);

    /**
     * Update resource method declaration
     */
    Customer updateCustomer(Long id, Customer customer);

    /**
     * Delete resource method declaration
     */
    void deleteCustomerById(Long id);

    /**
     * Get all resources method declaration
     */
    List<Customer> getCustomer();

    /**
     * Get a specific resource method declaration
     */
    Customer getCustomerById(Long id);

}