package com.example.practicecustomermanagement.service;

import com.example.practicecustomermanagement.model.Customer;
import com.example.practicecustomermanagement.specification.CustomerRequest;
import com.example.practicecustomermanagement.specification.PaginateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService extends IGeneralService<Customer> {
    Page<Customer> findAll(Pageable pageable);
    Page<Customer> findName(PaginateRequest paginateRequest, CustomerRequest customerRequest);
}
