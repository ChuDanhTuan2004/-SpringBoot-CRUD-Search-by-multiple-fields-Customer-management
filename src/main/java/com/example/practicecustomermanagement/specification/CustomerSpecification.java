package com.example.practicecustomermanagement.specification;

import com.example.practicecustomermanagement.model.Customer;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerSpecification implements Specification<Customer> {
    private CustomerRequest customerRequest;

    public CustomerSpecification(CustomerRequest customerRequest) {
        this.customerRequest = customerRequest;
    }

    @Override
    public Predicate toPredicate(Root<Customer> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder) {

        List<Predicate> list = new ArrayList<>();
        if (customerRequest.getFirstName() != null) {
            list.add(criteriaBuilder.like(root.get("firstName"), "%" + customerRequest.getFirstName() + "%"));
        }
        if (customerRequest.getLastName() != null) {
            list.add(criteriaBuilder.like(root.get("lastName"), "%" + customerRequest.getLastName() + "%"));
        }
        query.where(list.toArray(new Predicate[0]));

        return query.getRestriction();
    }


}
