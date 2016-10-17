/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.controller;

import com.namph.models.TblDaily;
import com.namph.render.listbox.ListBoxDaiLyRender;
import com.namph.service.DaiLyService;
import com.namph.utils.Constants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

/**
 * Sep 18, 2016
 *
 * @author Pham_Huy_Nam
 */
public class DailyController extends GenericForwardComposer<Div> {

    private Listbox tblAgency;
    private Div winAgency;
//    private DaiLyService dailyService;

    @Override
    public void doAfterCompose(Div comp) throws Exception {
        super.doAfterCompose(comp); //To change body of generated methods, choose Tools | Templates.
        init();
    }

    private void init() {
        List<TblDaily> lstAll = ((DaiLyService) SpringUtil.getBean("dailyService")).getAllDaiLy();
        ListModel<TblDaily> khoModel = new ListModelList<TblDaily>(lstAll);
        tblAgency.setModel(khoModel);
        tblAgency.setItemRenderer(new ListBoxDaiLyRender(winAgency));
        tblAgency.setMultiple(true);
    }

    public void onClick$tblAddAgency() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put(Constants.PARENT_WINDOW, winAgency);
        args.put(Constants.ACTION_ADD, true);
        Window winp = ((Window) Executions.getCurrent().createComponents("/pages/categories/agency/add.zul",
                null, args));
        winp.doModal();
    }

    public void onLoadDataCRUD(Event event) throws ComponentNotFoundException {
        init();
    }

}
