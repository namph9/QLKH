/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.controller;

import com.namph.service.ExportService;
import com.namph.service.ImportService;
import com.namph.service.TonKhoService;
import com.namph.utils.Utils;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zul.Div;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;

/**
 *
 * @author namph
 */
public class DashboardController extends GenericForwardComposer<Div> {

    private Label totalDebit, totalToday, totalMonths, importMonths;

    @Override
    public void doAfterCompose(Div comp) throws Exception {
        super.doAfterCompose(comp); //To change body of generated methods, choose Tools | Templates.
        init();
    }

    private void init() {
        double total_debit = ((TonKhoService) SpringUtil.getBean("tonKhoService"))
                .getSumDebit();
        totalDebit.setValue(Utils.customFormat("###,###.###", total_debit));

        double totalDay = ((ExportService) SpringUtil.getBean("exportService")).
                getTotalDay();

        totalToday.setValue(Utils.customFormat("###,###.###", totalDay));
        double totalMonth = ((ExportService) SpringUtil.getBean("exportService")).
                getTotalMonth();

        totalMonths.setValue(Utils.customFormat("###,###.###", totalMonth));

        double importMonth = ((ImportService) SpringUtil.getBean("importService"))
                .getTotalMonth();
        importMonths.setValue(Utils.customFormat("###,###.###", importMonth));

    }

}
