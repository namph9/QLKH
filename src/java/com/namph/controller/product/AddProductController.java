/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.controller.product;

import com.namph.models.TblProductType;
import com.namph.models.TblProducts;
import com.namph.render.selectbox.TypeRender;
import com.namph.service.ProductService;
import com.namph.service.TypeService;
import com.namph.utils.Constants;
import com.namph.utils.DateUtils;
import java.util.List;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author Huy_Nam
 */
public class AddProductController extends GenericForwardComposer<Window> {

    private Selectbox selectType;
    private Textbox txtCode, txtName, txtPrice, txtUnit;
    private Window modalProduct;

    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp); //To change body of generated methods, choose Tools | Templates.
        initSelect();
    }

    /**
     * load value to select box product type
     */
    private void initSelect() {
        List<TblProductType> lstAll = ((TypeService) SpringUtil.getBean("typeService")).getListType();
        ListModel<TblProductType> khoModel = new ListModelList<TblProductType>(lstAll);
        selectType.setModel(khoModel);
        selectType.setItemRenderer(new TypeRender());
    }

    public void onClick$btnSave() {
        int index = selectType.getSelectedIndex();
        List<TblProductType> lstAll = ((TypeService) SpringUtil.getBean("typeService")).getListType();

        TblProducts products = new TblProducts();
        products.setCode(txtCode.getValue().toUpperCase());
        products.setName(txtName.getValue());
        products.setUnit(txtUnit.getValue());
        products.setPrice(Double.parseDouble(txtPrice.getValue()));
        products.setInsertDate(DateUtils.getCurrent());
        products.setType(lstAll.get(index));
        ((ProductService) SpringUtil.getBean("productService")).insertProducts(products);
        modalProduct.detach();
        Div parent = (Div) arg.get(Constants.PARENT_WINDOW);
        Events.sendEvent("onLoadDataCRUD", parent, null);

    }

}
