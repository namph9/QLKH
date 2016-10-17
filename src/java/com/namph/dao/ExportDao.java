/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.dao;

import com.namph.models.TblExport;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Huy_Nam
 */
public interface ExportDao {

    List<TblExport> getListExport();

    List<TblExport> getListExportByDate(Date from, Date to);

    int getMaxSoHDExport();

    void saveExport(TblExport exp);

    void deleteExport(TblExport exp);

    double getTotalDay();

    double getTotalMonth();

}
