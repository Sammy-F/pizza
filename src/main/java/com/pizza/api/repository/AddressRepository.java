package com.pizza.api.repository;

import com.pizza.api.model.Address;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {}