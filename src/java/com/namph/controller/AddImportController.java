/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.controller;

import com.namph.models.Import;
import com.namph.models.KhoHang;
import com.namph.models.TblImportDetail;
import com.namph.models.TblProductType;
import com.namph.models.TblProducts;
import com.namph.render.combobox.ComboProducts;
import com.namph.render.combobox.ComboTypeRender;
import com.namph.render.selectbox.KhoHangRender;
import com.namph.service.ImportService;
import com.namph.service.KhoService;
import com.namph.service.TypeService;
import com.namph.utils.Constants;
import com.namph.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author Huy_Nam
 */
public class AddImportController extends GenericForwardComposer<Window> {

    private Listbox tblAddDetail;
    private Selectbox selectKho;
    private TypeService typeService;
    private Textbox txtDoitac, txtSoHD;
    private Datebox date_import;
    private Label total;
    private Window modalShort;
    private double d_total = 0;
    int soHD;

    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp); //To change body of generated methods, choose Tools | Templates.
        init();
        typeService = (TypeService) SpringUtil.getBean("typeService");
        soHD = ((ImportService) SpringUtil.getBean("importService")).getMaxSoHDImport() + 1;
        txtSoHD.setValue(Utils.formatSoHD(soHD));
        txtSoHD.setDisabled(true);
    }

    private void init() {
        List<KhoHang> lstAll = ((KhoService) SpringUtil.getBean("khoService")).findAll();
        ListModel<KhoHang> khoModel = new ListModelList<KhoHang>(lstAll);
        selectKho.setModel(khoModel);
        selectKho.setItemRenderer(new KhoHangRender());
    }

    public void onSelect$selectKho() {
        selectKho.getSelectedIndex();
    }

    public void onClick$btnAddRow() {
        List<TblProductType> type = typeService.getListType();
        ListModel<TblProductType> model = new ListModelList<TblProductType>(type);
        final Listitem item = new Listitem();
        Listcell cell = new Listcell();
        final Combobox cbType = new Combobox();
        final Combobox cbProduct = new Combobox();
        final Label lblUnit = new Label();
        cbType.setModel(model);
        cbType.setWidth("100%");
        cbType.setItemRenderer(new ComboTypeRender(tblAddDetail, cbProduct));
        cbType.addEventListener(Events.ON_CHANGE, new EventListener<Event>() {

            @Override
            public void onEvent(Event t) throws Exception {
                TblProductType type = cbType.getSelectedItem().getValue();
                ListModel<TblProducts> model = new ListModelList<TblProducts>(type.getTblProductsList());
                cbProduct.setModel(model);
                cbProduct.setItemRenderer(new ComboProducts());
            }
        });
        cell.setSclass("col-md-3");
        cell.appendChild(cbType);
        item.appendChild(cell);
        /*san pham*/
        Listcell cell1 = new Listcell();
        cbProduct.setWidth("100%");
        cbProduct.addEventListener(Events.ON_CHANGE, new EventListener<Event>() {

            @Override
            public void onEvent(Event t) throws Exception {
                TblProducts products = cbProduct.getSelectedItem().getValue();
                lblUnit.setValue(products.getUnit());
            }
        });
        cell1.setSclass("col-md-3");
        cell1.appendChild(cbProduct);
        item.appendChild(cell1);
        /*gia thanh*/
        cell = new Listcell();
        final Textbox lblPrice = new Textbox();// Gia thanh
        lblPrice.setSclass("form-control");
        lblPrice.addEventListener(Events.ON_CHANGE, new EventListener<Event>() {
            @Override
            public void onEvent(Event t) throws Exception {
                lblPrice.setValue(Utils.customFormat("###,###.###", Double.parseDouble(lblPrice.getValue())));
                calculatorTotal();
            }
        });
        cell.appendChild(lblPrice);
        item.appendChild(cell);
        /*so luong*/
        cell = new Listcell();
        Textbox txtCount = new Textbox();
        txtCount.setSclass("form-control");
        txtCount.addEventListener(Events.ON_CHANGE, new EventListener<Event>() {
            @Override
            public void onEvent(Event t) throws Exception {
                calculatorTotal();
            }
        });
        cell.appendChild(txtCount);
        item.appendChild(cell);
        cell = new Listcell();
        lblUnit.setSclass("form-control");
        cell.appendChild(lblUnit);
        item.appendChild(cell);
        cell = new Listcell();
        final Button btnDel = new Button();
        btnDel.setStyle("border:none;color: red");
        btnDel.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event t) throws Exception {
                tblAddDetail.removeChild(item);
                calculatorTotal();
            }
        });
        btnDel.setIconSclass("fa fa-trash-o fa-2x");
        cell.appendChild(btnDel);
        item.appendChild(cell);
        tblAddDetail.appendChild(item);
    }

    public void onClick$btnSave() {
        Import imp = new Import();
        int index = selectKho.getSelectedIndex();
        Integer houseId = ((KhoService) SpringUtil.getBean("khoService")).findAll().get(index).getId();
        imp.setHouseId(houseId);
        imp.setInsertDate(date_import.getValue());
        imp.setName(txtDoitac.getValue().toUpperCase());
        imp.setSoHD(soHD);
        imp.setTonKho(false);
        imp.setTotal(d_total);

        List<TblImportDetail> lstDetail = new ArrayList<TblImportDetail>();
        List<Listitem> lst = tblAddDetail.getItems();
        for (Listitem item : lst) {
            TblImportDetail detail = new TblImportDetail();
            detail.setTonKho(false);
            detail.setSoHD(imp);
            detail.setInsertDate(date_import.getValue());
            boolean flag = false;
            for (Component cell : item.getChildren()) {
                if (cell.getChildren().get(0) instanceof Combobox) {
                    Object obj = ((Combobox) cell.getChildren().get(0)).getSelectedItem().getValue();
                    if (obj instanceof TblProducts) {
                        TblProducts sp = (TblProducts) obj;
                        detail.setMaSP(sp);
                    }
                } else if (cell.getChildren().get(0) instanceof Textbox) {
                    if (flag) {
                        String count = ((Textbox) cell.getChildren().get(0)).getValue();
                        detail.setCount(Double.parseDouble(count));
                    } else {
                        String price = ((Textbox) cell.getChildren().get(0)).getValue();
                        detail.setPrice(Double.parseDouble(price.replaceAll(",", "")));
                        flag = true;
                    }
                }
            }
            lstDetail.add(detail);
        }
        imp.setTblImportDetailList(lstDetail);
        ((ImportService) SpringUtil.getBean("importService")).saveImport(imp);
        modalShort.detach();
        Div parent = (Div) arg.get(Constants.PARENT_WINDOW);
        Events.sendEvent("onLoadDataCRUD", parent, null);
    }

    private void calculatorTotal() {
        List<Listitem> lst = tblAddDetail.getItems();
        double _total = 0;
        for (Listitem item : lst) {
            boolean flag = false;
            double count = 0;
            double price = 0;
            for (Component cell : item.getChildren()) {
                if (cell.getChildren().get(0) instanceof Combobox) {
                } else if (cell.getChildren().get(0) instanceof Textbox) {
                    if (flag) {
                        String _count = ((Textbox) cell.getChildren().get(0)).getValue();
                        if (!"".equals(_count.trim())) {
                            count = (Double.parseDouble(_count));
                        }
                    } else {
                        String _price = ((Textbox) cell.getChildren().get(0)).getValue();
                        if (!"".equals(_price.trim())) {
                            price = (Double.parseDouble(_price.replaceAll(",", "")));
                        }
                        flag = true;
                    }
                    _total += count * price;
                }
            }
        }
        total.setValue(Utils.customFormat("###,###.###", _total));

        d_total = _total;
    }

}
