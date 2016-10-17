/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.controller;

import com.namph.models.TblProducts;
import com.namph.render.listbox.ListBoxProductsRender;
import com.namph.service.ProductService;
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
public class ProductsController extends GenericForwardComposer<Div> {

    private Div winProduct;
    private Listbox tblProduct;

    @Override
    public void doAfterCompose(Div comp) throws Exception {
        super.doAfterCompose(comp); //To change body of generated methods, choose Tools | Templates.
        winProduct = comp;
        init();
    }

    private void init() {
        List<TblProducts> lstPro = ((ProductService) SpringUtil.getBean("productService")).getAllProducts();
        ListModel<TblProducts> typeModel = new ListModelList<TblProducts>(lstPro);
        tblProduct.setModel(typeModel);
        tblProduct.setItemRenderer(new ListBoxProductsRender(winProduct));
        tblProduct.setMultiple(true);
    }

    public void onClick$btnAddPro() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put(Constants.PARENT_WINDOW, winProduct);
        args.put(Constants.ACTION_ADD, true);
        Window winp = ((Window) Executions.getCurrent().createComponents("/pages/categories/products/add.zul",
                null, args));
        winp.doModal();
    }

    public void onLoadDataCRUD(Event event) throws ComponentNotFoundException {
        init();
    }

    public void onDelete(Event event) {
        final TblProducts products = (TblProducts) event.getData();
        Messagebox.show("Bạn chắc chắn muốn xóa?",
                "Question", Messagebox.OK | Messagebox.CANCEL,
                Messagebox.QUESTION, new EventListener() {

                    @Override
                    public void onEvent(Event t) throws Exception {
                        if (Messagebox.ON_OK.equals(t.getName())) {
                            ((ProductService) SpringUtil.getBean("productService")).deleteProducts(products);
                            init();
                        }
                    }
                });
    }

    public void onLock(Event event) {
        final TblProducts products = (TblProducts) event.getData();
        Messagebox.show(products.isLock() ? "Bạn chắc chắn muốn khoá?" : "Bạn chắc chắn muốn mở khoá?",
                "Question", Messagebox.OK | Messagebox.CANCEL,
                Messagebox.QUESTION, new EventListener() {

                    @Override
                    public void onEvent(Event t) throws Exception {
                        if (Messagebox.ON_OK.equals(t.getName())) {
                            ((ProductService) SpringUtil.getBean("productService")).lockProducts(products);
                            init();
                        }
                    }
                });
    }
}
