/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.dao.impl;

import com.namph.dao.BasicDAO;
import com.namph.dao.UserDao;
import com.namph.models.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author namph
 */
public class UserDaoImpl extends BasicDAO<User> implements UserDao {

    @Override
    public User getUser(String userId, String pwd) {
        Session session = getSession();
        try {
            Criteria cri = session.createCriteria(User.class);
            cri.add(Restrictions.eq("login", userId));
            cri.add(Restrictions.eq("pwd", pwd));
            cri.add(Restrictions.eq("enabled", true));
            List<User> lstUser = cri.list();
            if (null != lstUser && !lstUser.isEmpty()) {
                return lstUser.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
