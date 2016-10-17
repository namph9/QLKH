/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.controller.export;

import com.namph.models.KhoHang;
import com.namph.models.TblDaily;
import com.namph.models.TblExport;
import com.namph.models.TblExportDetail;
import com.namph.models.TblProductType;
import com.namph.models.TblProducts;
import com.namph.models.TblThu;
import com.namph.render.combobox.ComboProducts;
import com.namph.render.combobox.ComboTypeRender;
import com.namph.render.combobox.DaiLyRender;
import com.namph.render.selectbox.KhoHangRender;
import com.namph.service.DaiLyService;
import com.namph.service.ExportService;
import com.namph.service.KhoService;
import com.namph.service.ThuService;
import com.namph.service.TonKhoService;
import com.namph.service.TypeService;
import com.namph.utils.Constants;
import com.namph.utils.DateUtils;
import com.namph.utils.Utils;
import com.namph.utils.dto.TonKhoDto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author Huy_Nam
 */
public class ModalExportController extends GenericForwardComposer<Window> {

    private Textbox txtSoHD, txtAddress;
    private Datebox date_import;
    private Window modalExport;
    private Listbox tblExportDetail;
    private Selectbox selectKho;
    private Combobox txtDoitac;
    private TypeService typeService;
    private Label total;
    private Radiogroup radioTT;
    private double d_total = 0;
    int soHD;

    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp); //To change body of generated methods, choose Tools | Templates.
        init();
        typeService = (TypeService) SpringUtil.getBean("typeService");
        soHD = ((ExportService) SpringUtil.getBean("exportService")).getMaxSoHDExport() + 1;
        txtSoHD.setValue(Utils.formatSoHD(soHD));
        txtSoHD.setDisabled(true);
    }

    private void init() {
        List<KhoHang> lstAll = ((KhoService) SpringUtil.getBean("khoService")).findAll();
        ListModel<KhoHang> khoModel = new ListModelList<KhoHang>(lstAll);
        selectKho.setModel(khoModel);
        selectKho.setItemRenderer(new KhoHangRender());

        List<TblDaily> lstDaiLy = ((DaiLyService) SpringUtil.getBean("dailyService")).getDaiLyRunning();
        ListModel<TblDaily> modelDaily = new ListModelList<TblDaily>(lstDaiLy);
        txtDoitac.setModel(modelDaily);
        txtDoitac.setItemRenderer(new DaiLyRender());

        total.setValue("0.0");

    }

    public void onChange$txtDoitac() {
        txtAddress.setValue(txtDoitac.getSelectedItem().getContext());
    }

    private void addNewRow(final boolean isKM) {
        List<TblProductType> type = typeService.getListType();
        ListModel<TblProductType> model = new ListModelList<TblProductType>(type);
        final Listitem item = new Listitem();
        item.setAttribute("KM", isKM);
        Listcell cell = new Listcell();
        final Combobox cbType = new Combobox();// combo loai san pham
        final Combobox cbProduct = new Combobox();// combo san pham
        final Label lblUnit = new Label();// don vi
        final Textbox txtPrice = new Textbox();// Gia thanh
        final Textbox txtCount = new Textbox();// so luong
        /*Tao cell loai san pham*/
        cbType.setModel(model);
        cbType.setWidth("100%");
        if (isKM) {
            cbType.setStyle("color: red");
        }
        cbType.setItemRenderer(new ComboTypeRender(tblExportDetail, cbProduct));
        cbType.addEventListener(Events.ON_CHANGE, new EventListener<Event>() {

            @Override
            public void onEvent(Event t) throws Exception {
                List<TonKhoDto> lstTonKho = ((TonKhoService) SpringUtil.getBean("tonKhoService"))
                        .getLstTonKho(DateUtils.getMonthCurr(), DateUtils.getYearCurr());
                TblProductType type = cbType.getSelectedItem().getValue();
                List<TblProducts> lstPro = type.getTblProductsList();

                Set<TblProducts> lstProS = new HashSet<TblProducts>();
                for (TonKhoDto objTon : lstTonKho) {
                    for (TblProducts product : lstPro) {
                        if (objTon.getMsSp().equalsIgnoreCase(product.getCode())) {
                            product.setCount(objTon.getCount());
                        }
                        lstProS.add(product);
                    }
                }

                ListModel<TblProducts> model = new ListModelList<TblProducts>(lstProS);
                cbProduct.setModel(model);
                cbProduct.setItemRenderer(new ComboProducts());
            }
        });
        cell.setSclass("col-md-2");
        cell.appendChild(cbType);
        item.appendChild(cell);
        /*Tao cell san pham*/
        cell = new Listcell();
        cbProduct.setWidth("100%");
        if (isKM) {
            cbProduct.setStyle("color: red");
        }
        cbProduct.addEventListener(Events.ON_CHANGE, new EventListener<Event>() {

            @Override
            public void onEvent(Event t) throws Exception {
                TblProducts products = cbProduct.getSelectedItem().getValue();
                lblUnit.setValue(products.getUnit());
                txtPrice.setValue(Utils.customFormat("###,###.###", products.getPrice()));
                txtCount.setAttribute("count", cbProduct.getSelectedItem().
                        getContent().replace("----", "").trim());
            }
        });
        cell.setSclass("col-md-2");
        cell.appendChild(cbProduct);
        item.appendChild(cell);
        /*tao cell gia thanh*/
        cell = new Listcell();
        txtPrice.setSclass("form-control");
        txtPrice.addEventListener(Events.ON_CHANGE, new EventListener<Event>() {
            @Override
            public void onEvent(Event t) throws Exception {
                if (!isKM) {
                    calculatorTotal();
                    txtPrice.setValue(Utils.customFormat("###,###.###", Double.parseDouble(txtPrice.getValue())));
                }
            }
        });
        cell.appendChild(txtPrice);
        item.appendChild(cell);
        /*Tao cell so luong*/
        cell = new Listcell();

        txtCount.setSclass("form-control");
        txtCount.addEventListener(Events.ON_CHANGE, new EventListener<Event>() {
            @Override
            public void onEvent(Event t) throws Exception {
                Long count = Long.parseLong((String) txtCount.getAttribute("count"));
                if (count < Long.parseLong(txtCount.getValue())) {
                    Messagebox.show("Tồn kho chỉ còn: " + count, "Thông báo", Messagebox.OK, Messagebox.INFORMATION);
                    return;
                }
                if (!isKM) {
                    calculatorTotal();
                }
            }
        });
        cell.appendChild(txtCount);
        item.appendChild(cell);
        /*Tao cell don vi*/
        cell = new Listcell();
        lblUnit.setSclass("form-control");
        cell.appendChild(lblUnit);
        cell.setSclass("col-xs-1");
        item.appendChild(cell);
        /*Tao cell xoa*/
        cell = new Listcell();
        final Button btnDel = new Button();
        btnDel.setStyle("border:none;color: red");
        btnDel.setIconSclass("fa fa-trash-o fa-2x");
        btnDel.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event t) throws Exception {
                tblExportDetail.removeChild(item);
                calculatorTotal();
            }
        });
        cell.setSclass("col-xs-1");
        cell.appendChild(btnDel);
        item.appendChild(cell);
        tblExportDetail.appendChild(item);
    }

    public void onClick$btnAddRow() {
        addNewRow(false);
    }

    public void onClick$btnAddRowKM() {
        addNewRow(true);
    }

    private void calculatorTotal() {
        List<Listitem> lst = tblExportDetail.getItems();
        double _total = 0;
        for (Listitem item : lst) {
            boolean isKm = (Boolean) item.getAttribute("KM");
            if (!isKm) {
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
        }
        total.setValue(Utils.customFormat("###,###.###", _total));

        d_total = _total;
    }

    public void onClick$btnSave() {
        try {
            TblExport exp = new TblExport();
            int index = selectKho.getSelectedIndex();
            Integer houseId = ((KhoService) SpringUtil.getBean("khoService")).findAll().get(index).getId();
            exp.setHouseId(houseId);
            exp.setInsertDate(date_import.getValue());
            TblDaily daily = ((DaiLyService) SpringUtil.getBean("dailyService"))
                    .findByCode(txtDoitac.getSelectedItem().getValue().toString());
            exp.setName(daily);
            exp.setSoHd(soHD);
            exp.setTotal(d_total);
            
            List<TblExportDetail> lstDetail = new ArrayList<TblExportDetail>();
            List<Listitem> lst = tblExportDetail.getItems();
            for (Listitem item : lst) {
                boolean flag = false;
                boolean isKm = (Boolean) item.getAttribute("KM");
                TblExportDetail detail = new TblExportDetail();
                detail.setSoHd(exp);
                detail.setInsertDate(date_import.getValue());
                detail.setIsKm(isKm);
                for (Component cell : item.getChildren()) {
                    if (cell.getChildren().get(0) instanceof Combobox) {
                        Object obj = ((Combobox) cell.getChildren().get(0)).getSelectedItem().getValue();
                        if (obj instanceof TblProducts) {
                            TblProducts sp = (TblProducts) obj;
                            detail.setMaSp(sp);
//                        detail.setPrice(sp.getPrice());
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
            exp.setTblExportDetailList(lstDetail);
            ((ExportService) SpringUtil.getBean("exportService")).saveExport(exp);

            if (d_total > 0) {
                if (radioTT.getSelectedItem().getValue().equals("tm")) {
                    TblThu thu = new TblThu();
                    thu.setAgenCode(exp.getName().getCode());
                    thu.setInsertDate(exp.getInsertDate());
                    thu.setTotal(-d_total);
                    thu.setContent("Thu tiền hóa đơn số -" + soHD);
                    ((ThuService) SpringUtil.getBean("thuService")).insertThu(thu);
                }
            }
            modalExport.detach();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Div parent = (Div) arg.get(Constants.PARENT_WINDOW);
        Events.sendEvent("onLoadDataCRUD", parent, null);
    }
}
