/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.service;

import com.namph.models.User;

/**
 *
 * @author namph
 */
public interface UserService {
    User getUser(String userId, String pwd);
}
