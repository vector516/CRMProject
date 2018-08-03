package com.itheima.crm.service;

import com.itheima.crm.domain.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User find(User user);


    List<User> findAll();
}
