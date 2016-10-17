/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.dao.impl;

import com.namph.dao.BasicDAO;
import com.namph.dao.ImportDao;
import com.namph.models.Import;
import com.namph.utils.DateUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Huy_Nam
 */
public class ImportDaoImpl extends BasicDAO<Import> implements ImportDao {

    @Override
    public List<Import> getAll() {
        List<Import> lst = new ArrayList<Import>();
        Session session = getSession();
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(Import.class);
            lst = cri.list();
            for (Import i : lst) {
                Hibernate.initialize(i.getTblImportDetailList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    @Override
    public void saveImport(Import imp) {
        Session session = getSession();
        try {
            session.saveOrUpdate(imp);
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getMaxSoHDImport() {
        Session session = getSession();
        Integer maxSo = 0;
        try {
            Criteria criteria = session
                    .createCriteria(Import.class)
                    .setProjection(Projections.max("soHD"));
            maxSo = (Integer) criteria.uniqueResult();
            if (maxSo == null) {
                maxSo = 0;
            }
        } catch (Exception e) {
            maxSo = 0;
        }
        return maxSo;
    }

    @Override
    public List<Import> getListImportByDate(Date from, Date to) {
        Session session = getSession();
        List<Import> lst = new ArrayList<Import>();
        try {
            Criteria cri = session.createCriteria(Import.class);
            cri.add(Restrictions.le("insertDate", DateUtils.getFormattedToDateTime(to)));
            cri.add(Restrictions.ge("insertDate", DateUtils.getFormattedFromDateTime(from)));
             cri.add(Restrictions.eq("tonKho", false));
            lst = cri.list();
            for (Import i : lst) {
                Hibernate.initialize(i.getTblImportDetailList());
            }
        } catch (Exception e) {
        }
        return lst;
    }

    @Override
    public Import getImpByDateTonKho(int month, int year) {
        Session session = getSession();
        List<Import> lst = new ArrayList<Import>();
        Import imp = new Import();
        try {
            Criteria cri = session.createCriteria(Import.class);
            cri.add(Restrictions.sqlRestriction("MONTH(insert_date)= ?", month, Hibernate.INTEGER));
            cri.add(Restrictions.sqlRestriction("YEAR(insert_date)= ?", year, Hibernate.INTEGER));
            cri.add(Restrictions.eq("tonKho", true));
            lst = cri.list();
            if (lst != null && !lst.isEmpty()) {
                imp = lst.get(0);
                Hibernate.initialize(imp);
            }
        } catch (Exception e) {
        }
        return imp;
    }

    @Override
    public double getTotalMonth() {
        Session session = getSession();
        Double res = 0D;
        try {
            Criteria cri = session.createCriteria(Import.class);
            cri.add(Restrictions.le("insertDate", DateUtils.getFormattedToDateTime(DateUtils.getTimeVNNoFull())));
            cri.add(Restrictions.ge("insertDate", DateUtils.getFormattedFromDateTime(DateUtils.getFirtMonthCurr())));
            cri.setProjection(Projections.sum("total"));
            res = (Double) cri.uniqueResult();
            if (res == null) {
                return 0D;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

}
