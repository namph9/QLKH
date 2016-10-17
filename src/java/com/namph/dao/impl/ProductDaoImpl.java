/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.dao.impl;

import com.namph.dao.BasicDAO;
import com.namph.dao.ProductDao;
import com.namph.models.TblProducts;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Huy_Nam
 */
public class ProductDaoImpl extends BasicDAO<TblProducts> implements ProductDao {

    @Override
    public List<TblProducts> getAllProducts() {
        Session session = getSession();
        List<TblProducts> lst = new ArrayList<TblProducts>();
        try {
            Criteria cri = session.createCriteria(TblProducts.class);
            lst = cri.list();
        } catch (Exception e) {
        }
        return lst;
    }

    @Override
    public void insertProducts(TblProducts product) {
        Session session = getSession();
        try {
            session.saveOrUpdate(product);
            session.flush();
        } catch (Exception e) {
        }
    }

    @Override
    public void deleteProducts(TblProducts products) {
        Session session = getSession();
        session.delete(products);
        session.flush();
    }

    @Override
    public void lockProducts(TblProducts products) {
        if (products.isLock()) {
            products.setLock(false);
        } else {
            products.setLock(true);
        }
        Session session = getSession();
        session.saveOrUpdate(products);
        session.flush();
    }

    @Override
    public TblProducts getProductByName(String name) {
        Session session = getSession();
        List<TblProducts> lst = new ArrayList<TblProducts>();
        try {
            Criteria cri = session.createCriteria(TblProducts.class);
            cri.add(Restrictions.eq("code", name));
            lst = cri.list();
        } catch (Exception e) {
        }
        return lst.get(0);
    }

    
    
}
