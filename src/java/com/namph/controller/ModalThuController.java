/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.controller;

import com.namph.models.TblDaily;
import com.namph.models.TblThu;
import com.namph.render.selectbox.DaiLySelectRender;
import com.namph.service.DaiLyService;
import com.namph.service.ThuService;
import com.namph.utils.Constants;
import com.namph.utils.DateUtils;
import java.util.List;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 * Oct 9, 2016
 *
 * @author Pham_Huy_Nam
 */
public class ModalThuController extends GenericForwardComposer<Window> {

    private Selectbox selectAgent;
    private Textbox txtAddress, txtContent;
    private Doublebox dbCurrency;
    private Window modalThu;

    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp); //To change body of generated methods, choose Tools | Templates.
        init();
    }

    /**
     * khoi tao select box daily
     */
    private void init() {
        List<TblDaily> lstDaiLy = ((DaiLyService) SpringUtil.getBean("dailyService")).getDaiLyRunning();
        ListModel<TblDaily> modelDaily = new ListModelList<TblDaily>(lstDaiLy);
        selectAgent.setModel(modelDaily);
        selectAgent.setItemRenderer(new DaiLySelectRender());
    }

    public void onSelect$selectAgent() {
        TblDaily tblDl = (TblDaily) selectAgent.getModel().getElementAt(selectAgent.getSelectedIndex());
        txtAddress.setText(tblDl.getAddress());

    }

    public void onClick$btnSave() {
        try {
            TblDaily tblDl = (TblDaily) selectAgent.getModel().getElementAt(selectAgent.getSelectedIndex());
            TblThu thu = new TblThu();
            thu.setAgenCode(tblDl.getCode());
            thu.setInsertDate(DateUtils.getTimeVN());
            thu.setTotal(-dbCurrency.getValue());
            thu.setContent(txtContent.getValue());
            ((ThuService) SpringUtil.getBean("thuService")).insertThu(thu);
            modalThu.detach();
            Messagebox.show("Thành công", "Thông báo", Messagebox.OK, Messagebox.INFORMATION);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Div parent = (Div) arg.get(Constants.PARENT_WINDOW);
        Events.sendEvent("onLoadDataCRUD", parent, null);
    }
}
