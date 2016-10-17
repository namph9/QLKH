/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.dao.impl;

import com.namph.dao.BasicDAO;
import com.namph.dao.ExportDao;
import com.namph.models.TblExport;
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
public class ExportDaoImpl extends BasicDAO<TblExport> implements ExportDao {

    /**
     * get list export by criteria: date from, date to
     *
     * @return
     */
    @Override
    public List<TblExport> getListExport() {
        Session session = getSession();
        List<TblExport> lst = new ArrayList<TblExport>();
        try {
            Criteria cri = session.createCriteria(TblExport.class);
            lst = cri.list();
            for (TblExport i : lst) {
                Hibernate.initialize(i.getTblExportDetailList());
            }
        } catch (Exception e) {
        }
        return lst;
    }

    /*Lay so hoa don lon nhat trong co so du lieu de tao so hoa don tiep theo*/
    @Override
    public int getMaxSoHDExport() {
        Session session = getSession();
        Integer maxSo = 0;
        try {
            Criteria criteria = session
                    .createCriteria(TblExport.class)
                    .setProjection(Projections.max("soHd"));
            maxSo = (Integer) criteria.uniqueResult();
            if (maxSo == null) {
                maxSo = 0;
            }
        } catch (Exception e) {
            maxSo = 0;
        }
        return maxSo;
    }

    /*Luu du lieu xuat hang xuong co so du lieu*/
    @Override
    public void saveExport(TblExport exp) {
        Session session = getSession();
        try {
            session.save(exp);
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Xoa du lieu xuat hang- xoa ca chi tiet luon vi dang de cascade*/
    @Override
    public void deleteExport(TblExport exp) {
        Session session = getSession();
        session.delete(exp);
        session.flush();
    }

    /*Lay danh sach don hang xuat theo ngay thang*/
    @Override
    public List<TblExport> getListExportByDate(Date from, Date to) {
        Session session = getSession();
        List<TblExport> lst = new ArrayList<TblExport>();
        try {
            Criteria cri = session.createCriteria(TblExport.class);
            cri.add(Restrictions.le("insertDate", DateUtils.getFormattedToDateTime(to)));
            cri.add(Restrictions.ge("insertDate", DateUtils.getFormattedFromDateTime(from)));
            lst = cri.list();
            for (TblExport i : lst) {
                Hibernate.initialize(i.getTblExportDetailList());
            }
        } catch (Exception e) {
        }
        return lst;
    }

    @Override
    public double getTotalDay() {
        Session session = getSession();
        Double res = 0D;
        try {
            Criteria cri = session.createCriteria(TblExport.class);
            cri.add(Restrictions.le("insertDate", DateUtils.getFormattedToDateTime(DateUtils.getTimeVNNoFull())));
            cri.add(Restrictions.ge("insertDate", DateUtils.getFormattedFromDateTime(DateUtils.getTimeVNNoFull())));
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

    @Override
    public double getTotalMonth() {
        Session session = getSession();
        Double res = 0D;
        try {
            Criteria cri = session.createCriteria(TblExport.class);
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
