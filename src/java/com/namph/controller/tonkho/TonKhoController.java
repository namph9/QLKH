/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.controller.tonkho;

import com.namph.render.listbox.ListBoxTonKhoRender;
import com.namph.service.TonKhoService;
import com.namph.utils.Constants;
import com.namph.utils.DateUtils;
import com.namph.utils.dto.TonKhoDto;
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
public class TonKhoController extends GenericForwardComposer<Div> {

    private Listbox tblTonKho;
    private Div winTonKho;

    @Override
    public void doAfterCompose(Div comp) throws Exception {
        super.doAfterCompose(comp); //To change body of generated methods, choose Tools | Templates.
        this.winTonKho = comp;
        init();
    }

    private void init() {
        int month = 0;
        int year = 0;
        try {
            month = DateUtils.getMonthCurr();
            year = DateUtils.getYearCurr();
        } catch (Exception e) {
        }
        List<TonKhoDto> lst = ((TonKhoService) SpringUtil.getBean("tonKhoService")).getLstTonKho(month, year);
        ListModel<TonKhoDto> modelTonKho = new ListModelList<TonKhoDto>(lst);
        tblTonKho.setModel(modelTonKho);
        tblTonKho.setItemRenderer(new ListBoxTonKhoRender());
        tblTonKho.setMultiple(true);
    }

    public void onClick$btnRefresh() {
        Map<String, Object> arguments = new HashMap<String, Object>();
        arguments.put(Constants.PARENT_WINDOW, winTonKho);
        Window winp = ((Window) Executions.getCurrent().createComponents("/pages/process/tonkho/refresh.zul",
                null, arguments));
        winp.doModal();
    }

    public void onLoadDataCRUD(Event event) throws ComponentNotFoundException {
        init();
    }
}
