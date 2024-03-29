package com.example.practicecustomermanagement.service;

import com.example.practicecustomermanagement.model.Customer;
import com.example.practicecustomermanagement.repository.CustomerRepository;
import com.example.practicecustomermanagement.specification.CustomerRequest;
import com.example.practicecustomermanagement.specification.CustomerSpecification;
import com.example.practicecustomermanagement.specification.PaginateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> findName(PaginateRequest paginateRequest, CustomerRequest customerRequest) {
        return customerRepository.findAll(new CustomerSpecification(customerRequest),
                PageRequest.of(paginateRequest.getPage(), paginateRequest.getSize()));
    }

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }

}
