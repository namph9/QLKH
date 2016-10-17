/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.dao.impl;

import com.namph.dao.BasicDAO;
import com.namph.dao.ThuDao;
import com.namph.models.TblThu;
import com.namph.models.ViewThuTien;
import com.namph.utils.DateUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Sep 25, 2016
 *
 * @author Pham_Huy_Nam
 */
public class ThuDaoImpl extends BasicDAO<TblThu> implements ThuDao {

    @Override
    public void insertThu(TblThu thu) {
        Session session = getSession();
        try {
            session.saveOrUpdate(thu);
            session.flush();
        } catch (Exception e) {
        }
    }

    @Override
    public List<ViewThuTien> findThu(Date from, Date to, String agentCode) {
        List<ViewThuTien> lst = new ArrayList<ViewThuTien>();
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            Criteria cri = session.createCriteria(ViewThuTien.class);
            cri.add(Restrictions.le("insertDate", DateUtils.getFormattedToDateTime(to)));
            cri.add(Restrictions.ge("insertDate", DateUtils.getFormattedFromDateTime(from)));
            if (!agentCode.trim().equals("")) {
                cri.add(Restrictions.like("agenCode", agentCode));
            }
            lst = cri.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lst;

    }

}
