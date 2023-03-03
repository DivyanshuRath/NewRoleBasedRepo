package com.deloitte.newproject.util;

import com.deloitte.newproject.model.Customer;

public class CustomerTestUtil {
    public static Customer createCustomer(Long id, String name) {

        Customer customer = new Customer();
        customer.setCustomerId(id);
        customer.setCustomerName(name);

        return customer;
    }
}
