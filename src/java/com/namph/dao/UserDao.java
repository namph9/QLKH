/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.dao;

import com.namph.models.User;

/**
 *
 * @author namph
 */
public interface UserDao {
    User getUser(String userId, String pwd);
}
