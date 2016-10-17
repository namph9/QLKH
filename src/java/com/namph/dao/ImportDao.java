/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.dao;

import com.namph.models.Import;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Huy_Nam
 */
public interface ImportDao {

    List<Import> getAll();

    void saveImport(Import imp);

    int getMaxSoHDImport();

    List<Import> getListImportByDate(Date from, Date to);

    Import getImpByDateTonKho(int month, int year);
    
    double getTotalMonth();
}
