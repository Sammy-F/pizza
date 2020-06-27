package com.pizza.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.pizza.api.exception.ResourceNotFoundException;
import com.pizza.api.model.Address;
import com.pizza.api.model.Customer;
import com.pizza.api.repository.AddressRepository;
import com.pizza.api.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AddressController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

    /**
     * Get all addresses
     * @return
     */
    @GetMapping("/addresses")
    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }
    
    /**
     * Gets all addresses of a given customer
     * @param customerId
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/customers/{customer_id}/addresses")
    public List<Address> getCustomerAddresses(@PathVariable(value="customer_id") Long customerId)
            throws ResourceNotFoundException {

        Customer customer = customerRepository
            .findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found for ID: " + customerId));

        return customer.getAddresses();
    }

    /**
     * Creates a new address
     * @param customerId
     * @param address
     * @return
     * @throws ResourceNotFoundException
     */
    @PostMapping("/customers/{customer_id}/addresses")
    public Address createAddress(@PathVariable(value = "customer_id") Long customerId, @Valid @RequestBody Address address)
            throws ResourceNotFoundException {

        // Look up customer
        Customer customer = customerRepository
            .findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found for ID: " + customerId));

        // Assign customer to address
        address.setCustomer(customer);

        // Add address to customer's list
        customer.addAddress(address);

        // Save customer, should cascade and save the address too
        customerRepository.save(customer);

        return address;
    }

    /**
     * Deletes an address
     * @param addressId
     * @return
     * @throws ResourceNotFoundException
     */
    @DeleteMapping("/addresses/{address_id}") 
    public Map<String, Boolean> deleteAddress(@PathVariable(value="address_id") Long addressId) 
            throws ResourceNotFoundException {

        // Load address
        Address address = addressRepository
            .findById(addressId)
            .orElseThrow(() -> new ResourceNotFoundException("Address not found for ID: " + addressId));

        // Get the associated customer
        Customer customer = address.getCustomer();

        // Since orphanRemoval is set to true, the address will be automatically deleted
        customer.removeAddress(address);
        customerRepository.save(customer);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    
}