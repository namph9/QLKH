/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.service.impl;

import com.namph.dao.ExportDao;
import com.namph.models.TblExport;
import com.namph.service.ExportService;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Huy_Nam
 */
public class ExportServiceImpl implements ExportService {

    private transient ExportDao exportDao;

    public ExportDao getExportDao() {
        return exportDao;
    }

    public void setExportDao(ExportDao exportDao) {
        this.exportDao = exportDao;
    }

    @Override
    public List<TblExport> getListExport() {
        return exportDao.getListExport();
    }

    @Override
    public int getMaxSoHDExport() {
        return exportDao.getMaxSoHDExport();
    }

    @Override
    public void saveExport(TblExport exp) {
        exportDao.saveExport(exp);
    }

    @Override
    public void deleteExport(TblExport exp) {
        exportDao.deleteExport(exp);
    }

    @Override
    public List<TblExport> getListExportByDate(Date from, Date to) {
        return exportDao.getListExportByDate(from, to);
    }

    @Override
    public double getTotalDay() {
        return exportDao.getTotalDay();
    }

    @Override
    public double getTotalMonth() {
        return exportDao.getTotalMonth();
    }

}
