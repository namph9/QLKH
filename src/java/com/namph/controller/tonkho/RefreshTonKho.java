/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.controller.tonkho;

import com.namph.models.Import;
import com.namph.models.TblImportDetail;
import com.namph.models.TblProducts;
import com.namph.service.ImportService;
import com.namph.service.ProductService;
import com.namph.service.TonKhoService;
import com.namph.utils.Constants;
import com.namph.utils.DateUtils;
import com.namph.utils.dto.TonKhoDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

/**
 *
 * @author Huy_Nam
 */
public class RefreshTonKho extends GenericForwardComposer<Window> {

    private Window modalTonKho;
    private Datebox date;

    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp); //To change body of generated methods, choose Tools | Templates.
    }

    public void onClick$btnRefresh() {
        Date datenow = date.getValue();
        List<TonKhoDto> lst = ((TonKhoService) SpringUtil.getBean("tonKhoService")).
                getLstTonKho(DateUtils.getMonthOfDate(datenow) - 1, DateUtils.getYearOfDate(datenow));
        Import imp = ((ImportService) SpringUtil.getBean("importService")).
                getImpByDateTonKho(DateUtils.getMonthOfDate(datenow), DateUtils.getYearOfDate(datenow));
        if (imp.getId() == null) {
            datenow.setDate(1);
            imp.setInsertDate(datenow);
            imp.setTonKho(true);
            imp.setSoHD(((ImportService) SpringUtil.getBean("importService")).getMaxSoHDImport() + 1);
        }
        List<TblImportDetail> lstDetail = new ArrayList<TblImportDetail>();
        for (TonKhoDto dto : lst) {
            TblImportDetail detail = new TblImportDetail();
            detail.setTonKho(true);
            detail.setSoHD(imp);
            datenow.setDate(1);
            detail.setInsertDate(datenow);
            detail.setCount(dto.getCount());
            TblProducts product = ((ProductService) SpringUtil.getBean("productService")).getProductByName(dto.getMsSp());
            detail.setMaSP(product);
            lstDetail.add(detail);
        }
        imp.setTblImportDetailList(lstDetail);
        ((ImportService) SpringUtil.getBean("importService")).saveImport(imp);
        modalTonKho.detach();
        Div parent = (Div) arg.get(Constants.PARENT_WINDOW);
        Events.sendEvent("onLoadDataCRUD", parent, null);
    }
}
