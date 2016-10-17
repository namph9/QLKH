/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.service.impl;

import com.namph.dao.ThuDao;
import com.namph.models.TblThu;
import com.namph.models.ViewThuTien;
import com.namph.service.ThuService;
import java.util.Date;
import java.util.List;

/**
 * Sep 25, 2016
 *
 * @author Pham_Huy_Nam
 */
public class ThuServiceImpl implements ThuService {

    private transient ThuDao thuDao;

    public ThuDao getThuDao() {
        return thuDao;
    }

    public void setThuDao(ThuDao thuDao) {
        this.thuDao = thuDao;
    }

    @Override
    public void insertThu(TblThu thu) {
        thuDao.insertThu(thu);
    }

    @Override
    public List<ViewThuTien> findThu(Date from, Date to, String agentCode) {
        return thuDao.findThu(from, to, agentCode);
    }

}
