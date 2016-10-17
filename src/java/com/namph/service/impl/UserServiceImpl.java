/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.service.impl;

import com.namph.dao.UserDao;
import com.namph.models.User;
import com.namph.service.UserService;

/**
 *
 * @author namph
 */
public class UserServiceImpl implements UserService {

    private transient UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUser(String userId, String pwd) {
        return userDao.getUser(userId, pwd);
    }

}
