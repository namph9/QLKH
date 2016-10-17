/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.service;

import com.namph.models.TblProducts;
import java.util.List;

/**
 *
 * @author Huy_Nam
 */
public interface ProductService {

    List<TblProducts> getAllProducts();

    void insertProducts(TblProducts product);
    
    void deleteProducts(TblProducts products);
    
    void lockProducts(TblProducts products);
    
    TblProducts getProductByName(String code);
}
