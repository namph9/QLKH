/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.render.listbox;

import com.namph.models.ViewThuTien;
import com.namph.utils.ComponentUtils;
import com.namph.utils.Constants;
import com.namph.utils.DateUtils;
import com.namph.utils.Utils;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

/**
 * Oct 2, 2016
 *
 * @author Pham_Huy_Nam
 */
public class ListBoxThuRender implements ListitemRenderer<ViewThuTien> {

    private final Div parent;

    public ListBoxThuRender(Div parent) {
        this.parent = parent;
    }

    @Override
    public void render(Listitem item, ViewThuTien t, int index) throws Exception {
        item.appendChild(ComponentUtils.createListcell(Integer.toString(index + 1),
                Constants.STYLE_TEXT_ALIGN_CENTER, "col-sm-1", null));
        item.appendChild(ComponentUtils.createListcell(
                DateUtils.convertToString(t.getInsertDate(), "dd/MM/yyyy"),
                Constants.STYLE_TEXT_ALIGN_CENTER, "col-sm-2", null));
        item.appendChild(ComponentUtils.createListcell(t.getAgenCode(),
                Constants.STYLE_TEXT_ALIGN_CENTER, "col-sm-1", null));
        item.appendChild(ComponentUtils.createListcell(t.getName(),
                Constants.STYLE_TEXT_ALIGN_CENTER, "col-sm-2", null));
        item.appendChild(ComponentUtils.createListcell(
                Utils.customFormat("###,###.###", t.getTotal()),
                Constants.STYLE_TEXT_ALIGN_CENTER, "col-sm-1", null));
        item.appendChild(ComponentUtils.createListcell(t.getContent()));
    }

}
