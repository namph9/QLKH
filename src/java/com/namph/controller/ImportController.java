/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.controller;

import com.namph.render.listbox.ListBoxImportDetailRender;
import com.namph.render.listbox.ListBoxImportRender;
import com.namph.models.Import;
import com.namph.models.TblImportDetail;
import com.namph.service.ImportService;
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
import org.zkoss.zul.Window;

/**
 *
 * @author Huy_Nam
 */
public class ImportController extends GenericForwardComposer<Div> {

    private Div winImport;
    private Listbox tblImportMaster, tblImportDetail;
    private Datebox from, to;

    @Override
    public void doAfterCompose(Div comp) throws Exception {
        super.doAfterCompose(comp); //To change body of generated methods, choose Tools | Templates.
        init();
    }

    /*Khoi tao gia tri*/
    private void init() {
        List<Import> lstAll = ((ImportService) SpringUtil.getBean("importService")).
                getListImportByDate(from.getValue(), to.getValue());
        ListModel<Import> importModels = new ListModelList<Import>(lstAll);
        tblImportMaster.setModel(importModels);
        tblImportMaster.setItemRenderer(new ListBoxImportRender(winImport));
        tblImportMaster.setMultiple(true);
    }

    /*Click chi tiet*/
    public void onShowDetail(Event event) {
        final Import nhap = (Import) event.getData();
        List<TblImportDetail> lstAll = nhap.getTblImportDetailList();
        ListModel<TblImportDetail> importDetailModels = new ListModelList<TblImportDetail>(lstAll);
        tblImportDetail.setModel(importDetailModels);
        tblImportDetail.setItemRenderer(new ListBoxImportDetailRender(winImport));
        tblImportDetail.setMultiple(true);
    }

    public void onClick$btnAddImport() {
        Map<String, Object> arg = new HashMap<String, Object>();
        arg.put(Constants.PARENT_WINDOW, winImport);
        arg.put(Constants.ACTION_ADD, true);
        Window winp = ((Window) Executions.getCurrent().createComponents("/pages/process/import/add.zul",
                null, arg));
        winp.doModal();
    }

    /*tim kiem co dieu kien*/
    public void onClick$btnSearch() {
        init();
    }

    /*reload data when crud*/
    public void onLoadDataCRUD(Event event) throws ComponentNotFoundException {
        init();
    }

}
