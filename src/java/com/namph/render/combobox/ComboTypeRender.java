/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.render.combobox;

import com.namph.models.TblProductType;
import com.namph.models.TblProducts;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

/**
 *
 * @author Huy_Nam
 */
public class ComboTypeRender implements ComboitemRenderer<TblProductType> {

    private final Listbox parent;
    final Combobox cbProduct;

    public ComboTypeRender(Listbox parent, Combobox cbProduct) {
        this.parent = parent;
        this.cbProduct = cbProduct;
    }

    @Override
    public void render(Comboitem item, final TblProductType type, int index) throws Exception {
        item.setLabel(type.getName());
        item.setValue(type);
    }
}
