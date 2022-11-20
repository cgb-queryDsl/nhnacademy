package com.nhnmart.cs.repository;

import com.nhnmart.cs.domain.Customer;

import java.util.HashMap;
import java.util.Map;

public class CustomerRepositoryImpl implements CustomerRepository {

    private final Map<String, Customer> customerMap = new HashMap<>();

    @Override
    public boolean exists(String key) {
        return customerMap.containsKey(key);
    }

    @Override
    public Customer register(String id, String password, String name) {
        Customer customer = new Customer(id, password, name);
        customerMap.put(customer.getId(), customer);

        return customer;
    }

    @Override
    public Customer getCustomer(String key) {
        return exists(key) ? customerMap.get(key) : null;
    }

}
