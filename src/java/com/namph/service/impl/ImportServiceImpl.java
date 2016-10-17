/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.service.impl;

import com.namph.dao.ImportDao;
import com.namph.models.Import;
import com.namph.service.ImportService;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Huy_Nam
 */
public class ImportServiceImpl implements ImportService {

    private transient ImportDao importDao;

    public ImportDao getImportDao() {
        return importDao;
    }

    public void setImportDao(ImportDao importDao) {
        this.importDao = importDao;
    }

    @Override
    public List<Import> getAll() {
        return importDao.getAll();
    }

    @Override
    public void saveImport(Import imp) {
        importDao.saveImport(imp);
    }

    @Override
    public int getMaxSoHDImport() {
        return importDao.getMaxSoHDImport();
    }

    @Override
    public List<Import> getListImportByDate(Date from, Date to) {
        return importDao.getListImportByDate(from, to);
    }

    @Override
    public Import getImpByDateTonKho(int month, int year) {
        return importDao.getImpByDateTonKho(month, year);
    }

    @Override
    public double getTotalMonth() {
        return importDao.getTotalMonth();
    }

}
