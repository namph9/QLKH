/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.render.listbox;

import com.namph.models.TblDaily;
import com.namph.utils.ComponentUtils;
import com.namph.utils.Constants;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

/**
 * Sep 25, 2016
 *
 * @author Pham_Huy_Nam
 */
public class ListBoxDaiLyRender implements ListitemRenderer<TblDaily> {

    private final Div parent;

    public ListBoxDaiLyRender(Div parent) {
        this.parent = parent;
    }

    @Override
    public void render(Listitem item, TblDaily t, int index) throws Exception {
        item.appendChild(ComponentUtils.createListcell(Integer.toString(index + 1),
                Constants.STYLE_TEXT_ALIGN_CENTER, "col-sm-1", null));
        item.appendChild(ComponentUtils.createListcell(t.getCode(),
                Constants.STYLE_TEXT_ALIGN_CENTER, "col-sm-1", null));
        item.appendChild(ComponentUtils.createListcell(t.getName(),
                Constants.STYLE_TEXT_ALIGN_CENTER, "col-sm-2", null));
        item.appendChild(ComponentUtils.createListcell(t.getAddress()));
        item.appendChild(cellStatus(t.getStatus()));
    }

    private Listcell cellStatus(boolean status) {
        Listcell stt = new Listcell();
        if (status) {
            stt.setLabel("Đang hoạt động");
        } else {
            stt.setLabel("Tạm ngưng");
        }
        stt.setSclass("col-sm-2");
        return stt;
    }

}
