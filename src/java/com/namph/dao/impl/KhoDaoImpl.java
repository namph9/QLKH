/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.dao.impl;

import com.namph.dao.BasicDAO;
import com.namph.dao.KhoDao;
import com.namph.models.KhoHang;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Huy_Nam
 */
public class KhoDaoImpl extends BasicDAO<KhoHang> implements KhoDao {

    @Override
    public List<KhoHang> getAll() {
        List<KhoHang> lst = new ArrayList<KhoHang>();
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(KhoHang.class);
            lst = cri.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lst;
    }

    @Override
    public void deleteKho(KhoHang kho) {
        Session session = getSession();
        session.delete(kho);
        session.flush();
    }

}
