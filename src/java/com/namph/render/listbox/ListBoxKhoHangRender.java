/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.render.listbox;

import com.namph.models.KhoHang;
import com.namph.utils.ComponentUtils;
import com.namph.utils.Constants;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

/**
 *
 * @author Huy_Nam
 */
public class ListBoxKhoHangRender implements ListitemRenderer<KhoHang> {

    private final Div parent;

    public ListBoxKhoHangRender(Div parent) {
        this.parent = parent;
    }

    @Override
    public void render(Listitem item, KhoHang t, int index) throws Exception {
//        item.appendChild(ComponentUtils.createListcell(StringPool.BLANK, Constants.STYLE_TEXT_ALIGN_CENTER));
        item.appendChild(ComponentUtils.createListcell(Integer.toString(index + 1), Constants.STYLE_TEXT_ALIGN_CENTER, "col-sm-1", null));
        item.appendChild(ComponentUtils.createListcell(t.getCode()));
        item.appendChild(ComponentUtils.createListcell(t.getName()));
        item.appendChild(ComponentUtils.createListcell(t.getAddress()));
        item.appendChild(createStatus(t));
        item.appendChild(createAction(t));
        item.addForward(Events.ON_DOUBLE_CLICK, parent, "onShowDetail", t);
    }

    /**
     * display status of the house
     *
     * @param t
     * @return
     */
    private Listcell createStatus(KhoHang t) {
        Listcell status = new Listcell();
        if (t.getStatus()) {
            status.setLabel("Đang hoạt động");
        } else {
            status.setLabel("Không hoạt động");
            status.setSclass(Constants.RED);
        }
        return status;
    }

    private Listcell createAction(KhoHang t) {
        Listcell action = new Listcell();
        Hlayout hlayout = new Hlayout();
        hlayout.setSpacing("0");
        hlayout.appendChild(ComponentUtils.createButton(parent, "", "", Events.ON_CLICK, "onDelete", t, "fa fa-trash-o fa-2x", ""));
//        hlayout.appendChild(ComponentUtils.createButton("Cap nhat", "fa fa-pencil"));
//        if (t.getStatus()) {
//            hlayout.appendChild(ComponentUtils.createButton("Khoa", "fa fa-lock"));
//        } else {
//            hlayout.appendChild(ComponentUtils.createButton("Mo Khoa", "fa fa-unlock"));
//        }
        action.appendChild(hlayout);
        return action;
    }

}
