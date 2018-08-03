package com.itheima.crm.service.impl;

import com.itheima.crm.constant.CRMConstant;
import com.itheima.crm.dao.UserDao;
import com.itheima.crm.domain.User;
import com.itheima.crm.service.UserService;
import com.itheima.crm.utils.MD5Utils;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save(User user) {
        String s = MD5Utils.md5(user.getUser_password());
        String s1 = MD5Utils.md5(s);
        user.setUser_password(s1);
        System.out.println(s1);
        user.setUser_state(CRMConstant.ACTIVATED);
        userDao.save(user);
    }

    @Override
    public User find(User user) {
        String s = MD5Utils.md5(user.getUser_password());
        String s1 = MD5Utils.md5(s);
        System.out.println(s1);
        user.setUser_password(s1);
        User user2 = userDao.find(user);
        return user2;
    }
}
