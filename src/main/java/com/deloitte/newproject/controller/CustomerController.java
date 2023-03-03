package com.deloitte.newproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.deloitte.newproject.model.Customer;
import com.deloitte.newproject.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a controller. Controller is a component that handles incoming HTTP requests from clients and sends back HTTP responses.
 * Controllers typically contain methods annotated with annotations such as @RequestMapping, @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, etc.,
 * which map specific URL patterns to these methods.
 *
 */
@Slf4j
@RestController
@CrossOrigin
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    /**
     * This method is for getting the list of all resources from DB.
     * @return The list of resources only if exists in DB.
     */
    @Operation(summary = "Get the list of customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the customer",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No Customers found",
                    content = @Content) })
    @GetMapping("/customer")
    public List<Customer> getCustomer() {
        return customerService.getCustomer();
    }

    /**
     * This method is to get the specific resource by its id
     * @param id The resource id
     * @return The resource found with provided id
     */
    @Operation(summary = "Get the customer by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the customer",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content) })
    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    /**
     * This method is to add a resource to the existing list of resources
     * @param customer The request body for a resource to be saved successfully
     * @return The saved resource
     */
    @Operation(summary = "Save the customer with the provided request body content")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "customer is saved",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content) })
    @PostMapping("/customer")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    /**
     * This method is to update a specific resource if it exists in DB
     * @param id The id of the resource to be updated
     * @param customer The request body which has the update
     * @return The updated resource
     */
    @Operation(summary = "Update the customer with the provided request body content")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "customer is updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content) })
    @PutMapping("/customer/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    /**
     * This method is for deleting the resource with its id
     * @param id The id of the resource to be deleted
     */
    @Operation(summary = "Delete the customer by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "customer is deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content) })
    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }
}
