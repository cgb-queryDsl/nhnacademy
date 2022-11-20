package com.nhnmart.cs.repository;

import com.nhnmart.cs.domain.Customer;

public interface CustomerRepository {
    boolean exists(String key);

    Customer register(String id, String password, String name);

    Customer getCustomer(String key);
//    String getCustomerId(Customer customer);
//    String getCustomerPassword(Customer customer);
}
