/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.controller;

import com.namph.models.TblDaily;
import com.namph.service.DaiLyService;
import com.namph.utils.DateUtils;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import com.namph.utils.Constants;

/**
 * Sep 25, 2016
 *
 * @author Pham_Huy_Nam
 */
public class ModalDaiLyController extends GenericForwardComposer<Window> {

    private Textbox txtCode, txtName, txtAddress;
    private Window modalAgency;

    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void onClick$btnSave() {
        TblDaily daily = new TblDaily();
        daily.setCode(txtCode.getValue().toUpperCase());
        daily.setName(txtName.getValue());
        daily.setAddress(txtAddress.getValue());
        daily.setInsertDate(DateUtils.getCurrent());
        daily.setStatus(true);
        ((DaiLyService) SpringUtil.getBean("dailyService")).insertDaily(daily);
        modalAgency.detach();
        Div parent = (Div) arg.get(Constants.PARENT_WINDOW);
        Events.sendEvent("onLoadDataCRUD", parent, null);
    }
}
