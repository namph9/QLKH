/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.render.listbox;

import com.namph.models.TblProducts;
import com.namph.utils.ComponentUtils;
import com.namph.utils.Constants;
import com.namph.utils.Utils;
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
public class ListBoxProductsRender implements ListitemRenderer<TblProducts> {

    private final Div parent;

    public ListBoxProductsRender(Div div) {
        this.parent = div;
    }

    @Override
    public void render(Listitem item, TblProducts t, int index) throws Exception {
        item.appendChild(ComponentUtils.createListcell(Integer.toString(index + 1),
                Constants.STYLE_TEXT_ALIGN_CENTER, "col-xs-1", null));
        item.appendChild(ComponentUtils.createListcell(t.getCode()));
        item.appendChild(ComponentUtils.createListcell(t.getName()));
        item.appendChild(ComponentUtils.createListcell(Utils.customFormat("###,###.###", t.getPrice())));
        item.appendChild(ComponentUtils.createListcell(t.getUnit()));
        item.appendChild(ComponentUtils.createListcell(t.getType().getName()));
        item.appendChild(createAction(t));
    }

    private Listcell createAction(TblProducts t) {
        Listcell action = new Listcell();
        Hlayout hlayout = new Hlayout();
        hlayout.setSpacing("0");
        hlayout.appendChild(ComponentUtils.createButton(parent, "", "",
                Events.ON_CLICK, "onDelete", t, "fa fa-trash-o fa-2x", "red"));
        hlayout.appendChild(ComponentUtils.createButton(parent, "", "",
                Events.ON_CLICK, "onLock", t, !t.isLock() ? "fa fa-lock fa-2x" : "fa fa-unlock fa-2x", "green"));
        hlayout.appendChild(ComponentUtils.createButton(parent, "", "",
                Events.ON_CLICK, "onEdit", t, "fa fa-pencil fa-2x", "orange"));
        action.appendChild(hlayout);
        return action;
    }

}
