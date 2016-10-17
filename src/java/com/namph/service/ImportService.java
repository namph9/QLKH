/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.service;

import com.namph.models.Import;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Huy_Nam
 */
public interface ImportService {

    List<Import> getAll();

    List<Import> getListImportByDate(Date from, Date to);

    void saveImport(Import imp);

    int getMaxSoHDImport();

    Import getImpByDateTonKho(int month, int year);
    
    double getTotalMonth();
}
