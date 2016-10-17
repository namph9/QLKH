/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.dao.impl;

import com.namph.dao.BasicDAO;
import com.namph.dao.TypeDao;
import com.namph.models.TblProductType;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;

/**
 *
 * @author Huy_Nam
 */
public class TypeDaoImpl extends BasicDAO<TblProductType> implements TypeDao {

    @Override
    public List<TblProductType> getTypeProduct() {
        List<TblProductType> lst = new ArrayList<TblProductType>();
        Session session = getSession();
        try {
            Criteria cri = session.createCriteria(TblProductType.class);
            lst = cri.list();
            for (TblProductType i : lst) {
                Hibernate.initialize(i.getTblProductsList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    @Override
    public void insertType(TblProductType type) {
        Session session = getSession();
        try {
            session.save(type);
            session.flush();
        } catch (Exception e) {
        }
    }

}
