/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.render.listbox;

import com.namph.models.TblImportDetail;
import com.namph.utils.ComponentUtils;
import com.namph.utils.Constants;
import com.namph.utils.Utils;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

/**
 *
 * @author Huy_Nam
 */
public class ListBoxImportDetailRender implements ListitemRenderer<TblImportDetail> {

    private final Div parent;

    public ListBoxImportDetailRender(Div parent) {
        this.parent = parent;
    }

    @Override
    public void render(Listitem item, TblImportDetail t, int index) throws Exception {
        item.appendChild(ComponentUtils.createListcell(Integer.toString(index + 1), Constants.STYLE_TEXT_ALIGN_CENTER, "col-sm-1", null));
        item.appendChild(ComponentUtils.createListcell(t.getMaSP().getCode()));
        item.appendChild(ComponentUtils.createListcell(t.getMaSP().getName() + ""));
        item.appendChild(ComponentUtils.createListcell(t.getCount() + ""));
        item.appendChild(ComponentUtils.createListcell(t.getMaSP().getUnit()));
        item.appendChild(ComponentUtils.createListcell(Utils.customFormat("###,###.###", t.getPrice())));
        item.appendChild(ComponentUtils.createListcell(Utils.customFormat("###,###.###", (t.getPrice() * t.getCount()))));
    }

}
