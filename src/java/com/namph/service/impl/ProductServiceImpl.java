/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.service.impl;

import com.namph.dao.ProductDao;
import com.namph.models.TblProducts;
import com.namph.service.ProductService;
import java.util.List;

/**
 *
 * @author Huy_Nam
 */
public class ProductServiceImpl implements ProductService {

    private transient ProductDao productDao;

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<TblProducts> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public void insertProducts(TblProducts product) {
        productDao.insertProducts(product);
    }

    @Override
    public void deleteProducts(TblProducts products) {
        productDao.deleteProducts(products);
    }

    @Override
    public void lockProducts(TblProducts products) {
        productDao.lockProducts(products);
    }

    @Override
    public TblProducts getProductByName(String code) {
        return productDao.getProductByName(code);
    }

}
