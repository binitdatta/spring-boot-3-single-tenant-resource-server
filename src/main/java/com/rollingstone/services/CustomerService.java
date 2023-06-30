package com.rollingstone.services;


import com.rollingstone.model.Customer;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CustomerService {

    Customer save(Customer customer);
    Optional<Customer> get(long id);
    Page<Customer> getCustomersByPage(Integer pageNumber, Integer pageSize);
    void update(long id, Customer customer);
    void delete(long id);
}
