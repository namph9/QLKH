/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.dao;

import com.namph.models.TblProducts;
import java.util.List;

/**
 *
 * @author Huy_Nam
 */
public interface ProductDao {

    List<TblProducts> getAllProducts();

    void insertProducts(TblProducts product);

    void deleteProducts(TblProducts products);

    void lockProducts(TblProducts products);

    TblProducts getProductByName(String name);
}
