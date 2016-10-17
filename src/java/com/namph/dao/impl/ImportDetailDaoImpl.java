/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.dao.impl;

import com.namph.dao.BasicDAO;
import com.namph.dao.ImportDetailDao;
import com.namph.models.TblImportDetail;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Huy_Nam
 */
public class ImportDetailDaoImpl
        extends BasicDAO<TblImportDetail>
        implements ImportDetailDao {

    @Override
    public List<TblImportDetail> getDetailImport(long soHD) {
        List<TblImportDetail> lst = new ArrayList<TblImportDetail>();
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(TblImportDetail.class);
            lst = cri.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lst;
    }

}
