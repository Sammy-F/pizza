package com.pizza.api.repository;

import java.util.List;

import com.pizza.api.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE EXISTS (SELECT a FROM Address a WHERE a.zipcode = ?1 AND a.customer = c)")
    public List<Customer> findByZipcode(String zipcode);

}