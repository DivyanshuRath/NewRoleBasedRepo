package com.deloitte.newproject.controller;

import com.deloitte.newproject.model.Customer;
import com.deloitte.newproject.service.CustomerService;
import com.deloitte.newproject.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.deloitte.newproject.util.CustomerTestUtil.createCustomer;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    private Customer customer = new Customer();

    private List<Customer> customerList = new ArrayList<>();

    @BeforeEach
    public void init() {
        customer = createCustomer(1L,"name1");
        customerList.add(createCustomer(2L,"name2"));
        customerList.add(createCustomer(3L,"name3"));
    }


    @Test
    public void getCustomerTest() throws Exception {

        when(customerService.getCustomer())
        .thenReturn(customerList);

        mockMvc.perform(get("/customer"))
                .andExpect(status().isOk());

        Assertions.assertEquals(2,customerList.size());
    }

    @Test
    public void getCustomerByIdTest() throws Exception {

        when(customerService.getCustomerById(any(Long.class)))
                .thenReturn(customer);

        this.mockMvc.perform(get("/customer/{id}",1L)).andExpectAll(
                status().isOk()
        );

        Assertions.assertNotNull(customer);
    }

    @Test
    void saveCustomerTestSuccess() throws Exception {
        when(customerService.saveCustomer(any(Customer.class)))
        .thenReturn(customer);

        mockMvc.perform(post("/customer")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(customer)))
        .andExpectAll(status().isOk());

        Assertions.assertNotNull(customer);
        }

    @Test
    public void updateCustomerTest() throws Exception {

        when(customerService.updateCustomer(any(Long.class), any(Customer.class)))
                .thenReturn(customer);

        mockMvc.perform(get("/customer/{id}",1L)
            .content(objectMapper.writeValueAsString(customer)))
            .andExpectAll(status().isOk());

        Assertions.assertNotNull(customer);
    }

    @Test
    public void deleteCustomerTest() throws Exception {
        doNothing()
                .when(customerService).deleteCustomerById(any(Long.class));
        mockMvc.perform(get("/customer/{id}",1L))
        .andExpectAll(status().isOk());
        }
    @Test
    public void deleteCustomerTestFailure()throws Exception{

        doThrow(new ResourceNotFoundException("Customer Not Found"))
        .when(customerService).deleteCustomerById(any(Long.class));
        mockMvc.perform(delete("/customer/{id}",1L))
        .andExpectAll(status().is4xxClientError());
        }

    @Test
    public void updateCustomerTestFailure()throws Exception{

        doThrow(new ResourceNotFoundException("Customer Not Found"))
        .when(customerService).updateCustomer(1L,customer);
        mockMvc.perform(put("/customer/{id}",1L))
        .andExpectAll(status().is4xxClientError());
        }

    @Test
    public void getCustomerByIdTestFailure()throws Exception{

        doThrow(new ResourceNotFoundException("Customer Not Found"))
        .when(customerService).getCustomerById(1L);
        mockMvc.perform(get("/customer/{id}",1L))
        .andExpectAll(status().is4xxClientError());
        }

}