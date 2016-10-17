/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.service;

import com.namph.models.TblDaily;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface DaiLyService {
    List<TblDaily> getAllDaiLy();
    
    List<TblDaily> getDaiLyRunning();
    
    void insertDaily(TblDaily dl);
    
    TblDaily findByCode(String code);
}
