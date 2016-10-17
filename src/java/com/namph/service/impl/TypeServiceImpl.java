/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.service.impl;

import com.namph.dao.TypeDao;
import com.namph.models.TblProductType;
import com.namph.service.TypeService;
import java.util.List;

/**
 *
 * @author Huy_Nam
 */
public class TypeServiceImpl implements TypeService {

    private transient TypeDao typeDao;

    public TypeDao getTypeDao() {
        return typeDao;
    }

    public void setTypeDao(TypeDao typeDao) {
        this.typeDao = typeDao;
    }

    @Override
    public List<TblProductType> getListType() {
        return typeDao.getTypeProduct();
    }

    @Override
    public void insertType(TblProductType type) {
        typeDao.insertType(type);
    }

}
