package com.pizza.api.repository;

import java.util.List;

import com.pizza.api.model.Address;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    public List<Address> findByZipcode(String zipcode);

}