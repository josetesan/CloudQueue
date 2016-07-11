package com.codetron.cloud.queue.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by josetesan on 10/07/16.
 */
@Service
public class CustomerService {


    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer retrieveCustomer(final Long id) {
        return this.customerRepository.findOne(id);
    }

    public Customer saveCustomer(final Customer customer) {
        return this.customerRepository.save(customer);
    }


    public List<Customer> retrieveAll() {
        return this.customerRepository.findAll();
    }
}
