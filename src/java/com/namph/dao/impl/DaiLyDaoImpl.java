/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.dao.impl;

import com.namph.dao.BasicDAO;
import com.namph.dao.DaiLyDao;
import com.namph.models.TblDaily;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Sep 25, 2016
 *
 * @author Pham_Huy_Nam
 */
public class DaiLyDaoImpl extends BasicDAO<TblDaily> implements DaiLyDao {

    @Override
    public List<TblDaily> getAllDaiLy() {
        List<TblDaily> lst = new ArrayList<TblDaily>();
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(TblDaily.class);
            lst = cri.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lst;
    }

    @Override
    public void insertDaiLy(TblDaily dl) {
        Session session = getSession();
        try {
            session.saveOrUpdate(dl);
            session.flush();
        } catch (Exception e) {
        }
    }

    @Override
    public TblDaily findByCode(String code) {
        List<TblDaily> lst = new ArrayList<TblDaily>();
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(TblDaily.class);
            cri.add(Restrictions.eq("code", code));
            lst = cri.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lst.get(0);
    }

    @Override
    public List<TblDaily> getDaiLyRunning() {
        List<TblDaily> lst = new ArrayList<TblDaily>();
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(TblDaily.class);
            cri.add(Restrictions.eq("status", true));
            lst = cri.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lst;
    }

}
