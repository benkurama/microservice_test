package com.test.account.service.impl;

import com.test.account.entity.Customer;
import com.test.account.exception.ResourceNotFoundException;
import com.test.account.repository.CustomerRepository;
import com.test.account.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Optional<Customer> custemerDb = this.customerRepository.findById(customer.getId());

        if (custemerDb.isPresent()) {
            Customer customerUpdate = custemerDb.get();
            customerUpdate.setId(customer.getId());
            customerUpdate.setCustomerName(customer.getCustomerName());
            customerUpdate.setCustomerMobile(customer.getCustomerMobile());
            customerUpdate.setCustomerEmail(customer.getCustomerEmail());
            customerUpdate.setAddress1(customer.getAddress1());
            customerUpdate.setAddress2(customer.getAddress2());
            customerRepository.save(customerUpdate);
            return customerUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + customer.getId());
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(long customerId) {
        Optional <Customer> customerDB = this.customerRepository.findById(customerId);

        if (customerDB.isPresent()) {
            return customerDB.get();
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + customerId);
        }
    }

    @Override
    public void deleteCustomerById(long id) {
        Optional <Customer> customerDb = this.customerRepository.findById(id);

        if (customerDb.isPresent()) {
            this.customerRepository.delete(customerDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + id);
        }
    }
}
