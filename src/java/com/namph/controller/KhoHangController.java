/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.controller;

import com.namph.render.listbox.ListBoxKhoHangRender;
import com.namph.models.KhoHang;
import com.namph.service.KhoService;
import com.namph.utils.Constants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

/**
 *
 * @author Huy_Nam
 */
public class KhoHangController extends GenericForwardComposer<Div> {

    @Autowired
    private KhoService khoService;
    private Listbox tblHouse;
    private Div winKhoHang;

    @Override
    public void doAfterCompose(Div comp) throws Exception {
        super.doAfterCompose(comp); //To change body of generated methods, choose Tools | Templates.
        init();
    }

    private void init() {
        List<KhoHang> lstAll = ((KhoService) SpringUtil.getBean("khoService")).findAll();
        ListModel<KhoHang> khoModel = new ListModelList<KhoHang>(lstAll);
        tblHouse.setModel(khoModel);
        tblHouse.setItemRenderer(new ListBoxKhoHangRender(winKhoHang));
        tblHouse.setMultiple(true);
    }

    public void onDelete(Event event) {
        final KhoHang kho = (KhoHang) event.getData();
        Messagebox.show("Bạn chắc chắn muốn xóa?",
                "Question", Messagebox.OK | Messagebox.CANCEL,
                Messagebox.QUESTION, new EventListener() {

            @Override
            public void onEvent(Event t) throws Exception {
                if (Messagebox.ON_OK.equals(t.getName())) {
                    ((KhoService) SpringUtil.getBean("khoService")).deleteKho(kho);
                    init();
                }
            }
        });
    }

    public void onShowDetail(Event event) {
        final KhoHang kho = (KhoHang) event.getData();
        Messagebox.show(kho.getCode());
    }

    public void onClick$btnAdd() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put(Constants.PARENT_WINDOW, winKhoHang);
        args.put(Constants.ACTION_ADD, true);
        Window winp = ((Window) Executions.getCurrent().createComponents("/pages/categories/house/add.zul",
                null, args));
        winp.doModal();
    }

}
