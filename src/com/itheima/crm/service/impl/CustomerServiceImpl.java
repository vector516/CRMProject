package com.itheima.crm.service.impl;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {

        this.customerDao = customerDao;
    }


    @Override
    public void save(Customer customer) {

        customerDao.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> list = customerDao.findAll();
        return list;
    }

    @Override
    public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize) {
        PageBean<Customer> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        //查询总数据条数
        Long totalCount = customerDao.findCount(detachedCriteria);

        int totalCount2 = totalCount.intValue();
        pageBean.setTotalCount(totalCount2);

        //计算总页数
        int totalPage = totalCount2 / pageSize;
        if (totalCount2 % pageSize != 0) {
            totalPage++;
        }
        pageBean.setTotalPage(totalPage);
        List<Customer> list = customerDao.findByPage(detachedCriteria, currentPage, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public Customer findById(Long cust_id) {
        Customer customer = customerDao.findById(cust_id);

        return customer;
    }

    @Override
    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);

    }
}
