package com.itheima.crm.dao;

import com.itheima.crm.domain.User;

import java.util.List;

public interface UserDao {

    void save(User user);

    User find(User user);

    List<User> findAll();
}
