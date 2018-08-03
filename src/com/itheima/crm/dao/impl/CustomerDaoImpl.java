package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {


//    public CustomerDaoImpl() {
//        //通过spring创建Dao时将Customer.class传递到BaseDao
//        super(Customer.class);
//    }
}
