package com.tornado.demo.mapper;

import com.tornado.demo.entity.Customer;

public interface CustomerMapper {

    Customer selectCustomer(int id);

    void insertCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);
}
