/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.service.impl;

import com.namph.dao.ImportDetailDao;
import com.namph.models.TblImportDetail;
import com.namph.service.ImportDetailService;
import java.util.List;

/**
 *
 * @author Huy_Nam
 */
public class ImportDetailServiceImpl implements ImportDetailService{

    private transient ImportDetailDao importDetailDao;

    public ImportDetailDao getImportDetailDao() {
        return importDetailDao;
    }

    public void setImportDetailDao(ImportDetailDao importDetailDao) {
        this.importDetailDao = importDetailDao;
    }
    
    @Override
    public List<TblImportDetail> getDetailImport(long soHD) {
        return importDetailDao.getDetailImport(soHD);
    }
    
}
