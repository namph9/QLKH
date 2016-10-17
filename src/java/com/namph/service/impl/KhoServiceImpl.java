/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.service.impl;

import com.namph.service.KhoService;
import com.namph.dao.KhoDao;
import com.namph.models.KhoHang;
import java.util.List;

/**
 *
 * @author Huy_Nam
 */
public class KhoServiceImpl implements KhoService {

    private transient KhoDao khoDao;

    public KhoDao getKhoDao() {
        return khoDao;
    }

    public void setKhoDao(KhoDao khoDao) {
        this.khoDao = khoDao;
    }

    @Override
    public List<KhoHang> findAll() {
        return khoDao.getAll();
    }

    @Override
    public void deleteKho(KhoHang kho) {
        khoDao.deleteKho(kho);
    }

}
