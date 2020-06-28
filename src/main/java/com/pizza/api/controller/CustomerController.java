package com.pizza.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.pizza.api.exception.ResourceNotFoundException;
import com.pizza.api.model.Customer;
import com.pizza.api.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Get all customers
     * 
     * @return the list
     */
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Get a customer by their unique id
     * @param customerId
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/customers/{customer_id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "customer_id") Long customerId) 
            throws ResourceNotFoundException {
        Customer customer = customerRepository
            .findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found for ID: " + customerId));
        return ResponseEntity.ok().body(customer);
    }

    @GetMapping("/customers/zipcode/{zipcode}")
    public List<Customer> getCustomersByZipcode(@PathVariable(value = "zipcode") String zipcode) {
        return customerRepository.findByZipcode(zipcode);
    }
    
    /**
     * Create a new customer
     * @param customer
     * @return the new customer
     */
    @PostMapping("/customers")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Update an existing customer. Note, does NOT update password.
     * @param customerId  the id of the original customer
     * @param newCustomerDetails the details of the updated customer
     * @return the updated customer
     * @throws ResourceNotFoundException
     */
    @PutMapping("/customers/{customer_id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "customer_id") Long customerId, 
                                                   @Valid @RequestBody Customer newCustomerDetails)
            throws ResourceNotFoundException {
        Customer originalCustomer = customerRepository
            .findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found for ID: " + customerId));

        originalCustomer.setFirstName(newCustomerDetails.getFirstName());
        originalCustomer.setLastName(newCustomerDetails.getLastName());
        originalCustomer.setPhoneNumber(newCustomerDetails.getPhoneNumber());
        originalCustomer.setEmail(newCustomerDetails.getEmail());

        final Customer updatedCustomer = customerRepository.save(originalCustomer);
        return ResponseEntity.ok(updatedCustomer);
    }

    /**
     * Delete an existing customer
     * @param customerId
     * @return response
     * @throws ResourceNotFoundException
     */
    @DeleteMapping("/customers/{customer_id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "customer_id") Long customerId)
            throws ResourceNotFoundException {
        Customer customer = customerRepository
            .findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found for ID: " + customerId));

        customerRepository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();

        response.put("deleted", Boolean.TRUE);
        return response;
    }
 
}