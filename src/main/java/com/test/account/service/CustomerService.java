package com.test.account.service;

import com.test.account.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(long customerId);
    void deleteCustomerById(long id);
}
