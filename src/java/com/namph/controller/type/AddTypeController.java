/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.controller.type;

import com.namph.models.TblProductType;
import com.namph.service.TypeService;
import com.namph.utils.Constants;
import com.namph.utils.DateUtils;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author Huy_Nam
 */
public class AddTypeController extends GenericForwardComposer<Window> {

    private Textbox txtCode, txtName;
    private Window modalType;

    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp); //To change body of generated methods, choose Tools | Templates.
    }

    public void onClick$btnSave() {
        TblProductType type = new TblProductType();
        type.setCode(txtCode.getValue().toUpperCase());
        type.setName(txtName.getValue());
        type.setInsertDate(DateUtils.getCurrent());
        ((TypeService) SpringUtil.getBean("typeService")).insertType(type);
        modalType.detach();
        Div parent = (Div) arg.get(Constants.PARENT_WINDOW);
        Events.sendEvent("onLoadDataCRUD", parent, null);
    }
}
