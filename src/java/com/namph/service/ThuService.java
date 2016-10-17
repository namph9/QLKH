/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.service;

import com.namph.models.TblThu;
import com.namph.models.ViewThuTien;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface ThuService {
    void insertThu(TblThu thu );
    
    List<ViewThuTien> findThu(Date from, Date to, String agentCode);
}
