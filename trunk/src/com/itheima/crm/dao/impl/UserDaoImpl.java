package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.UserDao;
import com.itheima.crm.domain.User;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

import java.util.List;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

    @Override
    public void save(User user) {
        getHibernateTemplate().save(user);
    }

    @Override
    public User find(User user) {
        System.out.println(user);
        User user1=null;
        List<User> list = (List<User>) getHibernateTemplate().find("from  User where user_code =? and user_password =?", user.getUser_code(), user.getUser_password());
       if(list.size()>0) {
           user1 = list.get(0);
       }
        return user1;
    }
}
