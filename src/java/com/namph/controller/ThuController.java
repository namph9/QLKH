/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.controller;

import com.namph.models.ViewThuTien;
import com.namph.render.listbox.ListBoxThuRender;
import com.namph.service.ThuService;
import com.namph.utils.Constants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 * Oct 2, 2016
 *
 * @author Pham_Huy_Nam
 */
public class ThuController extends GenericForwardComposer<Div> {

    private Listbox tblThu;
    private Div winMoney;
    private Datebox from, to;
    private Textbox txtAgentCode;

    @Override
    public void doAfterCompose(Div comp) throws Exception {
        super.doAfterCompose(comp);
        init();
    }

    private void init() {
        try {

            List<ViewThuTien> lstAll = ((ThuService) SpringUtil.getBean("thuService")).
                    findThu(from.getValue(), to.getValue(), txtAgentCode.getValue());
            ListModel<ViewThuTien> khoModel = new ListModelList<ViewThuTien>(lstAll);
            tblThu.setModel(khoModel);
            tblThu.setItemRenderer(new ListBoxThuRender(winMoney));
            tblThu.setMultiple(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick$btnSearch() {
        init();
    }

    public void onClick$btnThu() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put(Constants.PARENT_WINDOW, winMoney);
        args.put(Constants.ACTION_ADD, true);
        Window winp = ((Window) Executions.getCurrent().createComponents("/pages/process/thu/add.zul",
                null, args));
        winp.doModal();
    }

    public void onLoadDataCRUD(Event event) throws ComponentNotFoundException {
        init();
    }
}
