/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.dao;

import com.namph.models.TblDaily;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface DaiLyDao {

    List<TblDaily> getAllDaiLy();

    List<TblDaily> getDaiLyRunning();

    void insertDaiLy(TblDaily dl);

    TblDaily findByCode(String code);
}
