/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.dao;

import com.namph.models.TblImportDetail;
import java.util.List;

/**
 *
 * @author Huy_Nam
 */
public interface ImportDetailDao {
    List<TblImportDetail> getDetailImport(long soHD);
}
