/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.controller.export;

import com.namph.models.TblExport;
import com.namph.models.TblExportDetail;
import com.namph.render.listbox.ListBoxExportDetailRender;
import com.namph.render.listbox.ListBoxExportRender;
import com.namph.service.ExportService;
import com.namph.utils.Constants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;
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
public class ExportController extends GenericForwardComposer<Div> {

    private Div winExport;
    private Listbox tblExportMaster, tblExportDetail;
    private Datebox from, to;

    @Override
    public void doAfterCompose(Div comp) throws Exception {
        super.doAfterCompose(comp); //To change body of generated methods, choose Tools | Templates.
        winExport = comp;
        init();
    }

    private void init() {
        List<TblExport> lstAll = ((ExportService) SpringUtil.getBean("exportService")).
                getListExportByDate(from.getValue(), to.getValue());
        ListModel<TblExport> importModels = new ListModelList<TblExport>(lstAll);
        tblExportMaster.setModel(importModels);
        tblExportMaster.setItemRenderer(new ListBoxExportRender(winExport));
        tblExportMaster.setMultiple(true);
    }

    /*Tim kiem don hang theo ngay thang*/
    public void onClick$btnSearch() {
        init();
    }

    /*them moi don hang xuat*/
    public void onClick$btnExport() {
        Map<String, Object> agrument = new HashMap<String, Object>();
        agrument.put(Constants.PARENT_WINDOW, winExport);
        agrument.put(Constants.ACTION_ADD, true);
        Window winp = ((Window) Executions.getCurrent().createComponents("/pages/process/export/add.zul",
                null, agrument));
        winp.doModal();
    }

    public void onLoadDataCRUD(Event event) throws ComponentNotFoundException {
        init();
    }

    public void onShowDetail(Event event) {
        final TblExport export = (TblExport) event.getData();
        List<TblExportDetail> lstAll = export.getTblExportDetailList();
        ListModel<TblExportDetail> importDetailModels = new ListModelList<TblExportDetail>(lstAll);
        tblExportDetail.setModel(importDetailModels);
        tblExportDetail.setItemRenderer(new ListBoxExportDetailRender(winExport));
        tblExportDetail.setMultiple(true);
    }

    public void onDeleteExport(Event event) {
        final TblExport export = (TblExport) event.getData();
        Messagebox.show("Bạn chắc chắn muốn xóa?",
                "Cảnh báo", Messagebox.OK | Messagebox.CANCEL,
                Messagebox.QUESTION, new EventListener() {

                    @Override
                    public void onEvent(Event t) throws Exception {
                        if (Messagebox.ON_OK.equals(t.getName())) {
                            ((ExportService) SpringUtil.getBean("exportService")).deleteExport(export);
                            init();
                        }
                    }
                });
    }
}
