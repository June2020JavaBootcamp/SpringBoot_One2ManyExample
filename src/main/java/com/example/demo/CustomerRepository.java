package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.Iterator;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Iterable<Customer> findAllByIdGreaterThanOrderByName(long id);

}
