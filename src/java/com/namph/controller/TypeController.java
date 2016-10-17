/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.controller;

import com.namph.models.TblProductType;
import com.namph.render.listbox.ListBoxTypeRender;
import com.namph.service.TypeService;
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
 *
 * @author Huy_Nam
 */
public class TypeController extends GenericForwardComposer<Div> {

    private Div winType;
    private Listbox tblProductType;

    @Override
    public void doAfterCompose(Div comp) throws Exception {
        super.doAfterCompose(comp); //To change body of generated methods, choose Tools | Templates.
        this.winType = comp;
        init();
    }

    private void init() {
        List<TblProductType> lstType = ((TypeService) SpringUtil.getBean("typeService")).getListType();
        ListModel<TblProductType> typeModel = new ListModelList<TblProductType>(lstType);
        tblProductType.setModel(typeModel);
        tblProductType.setItemRenderer(new ListBoxTypeRender());
        tblProductType.setMultiple(true);
    }

    public void onClick$tblAddType() {
        Map<String, Object> arg = new HashMap<String, Object>();
        arg.put(Constants.PARENT_WINDOW, winType);
        arg.put(Constants.ACTION_ADD, true);
        Window winp = ((Window) Executions.getCurrent().createComponents("/pages/categories/p_type/add.zul",
                null, arg));
        winp.doModal();
    }

    public void onLoadDataCRUD(Event event) throws ComponentNotFoundException {
        init();
    }
}
