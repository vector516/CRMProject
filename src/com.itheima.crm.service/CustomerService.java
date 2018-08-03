package com.itheima.crm.service;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerService {
    void save(Customer customer);

    List<Customer> findAll();

    PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize);

    Customer findById(Long cust_id);

    void delete(Customer customer);

    void update(Customer customer);
}
