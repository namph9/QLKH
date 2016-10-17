/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.service.impl;

import com.namph.dao.TonKhoDao;
import com.namph.service.TonKhoService;
import com.namph.utils.dto.TonKhoDto;
import java.util.List;

/**
 *
 * @author Huy_Nam
 */
public class TonKhoServiceImpl implements TonKhoService {

    private transient TonKhoDao tonKhoDao;

    public TonKhoDao getTonKhoDao() {
        return tonKhoDao;
    }

    public void setTonKhoDao(TonKhoDao tonKhoDao) {
        this.tonKhoDao = tonKhoDao;
    }

    @Override
    public List<TonKhoDto> getLstTonKho(int month, int year) {
        return tonKhoDao.getLstTonKho(month, year);
    }

    @Override
    public List<TonKhoDto> getLstCongno(String code) {
        return tonKhoDao.getLstCongno(code);
    }

    @Override
    public double getSumDebit() {
        return tonKhoDao.getSumDebit();
    }

}
