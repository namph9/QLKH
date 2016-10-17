/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.service.impl;

import com.namph.dao.DaiLyDao;
import com.namph.models.TblDaily;
import com.namph.service.DaiLyService;
import java.util.List;

/**
 * Sep 25, 2016
 *
 * @author Pham_Huy_Nam
 */
public class DaiLyServiceImpl implements DaiLyService {

    private transient DaiLyDao dailyDao;

    @Override
    public List<TblDaily> getAllDaiLy() {
        return dailyDao.getAllDaiLy();
    }

    public DaiLyDao getDailyDao() {
        return dailyDao;
    }

    public void setDailyDao(DaiLyDao dailyDao) {
        this.dailyDao = dailyDao;
    }

    @Override
    public void insertDaily(TblDaily dl) {
        dailyDao.insertDaiLy(dl);
    }

    @Override
    public TblDaily findByCode(String code) {
        return dailyDao.findByCode(code);
    }


    @Override
    public List<TblDaily> getDaiLyRunning() {
         return dailyDao.getDaiLyRunning();
    }

}
