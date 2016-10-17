/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.render.listbox;

import com.namph.models.TblExportDetail;
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
public class ListBoxExportDetailRender implements ListitemRenderer<TblExportDetail> {

    private final Div parent;

    public ListBoxExportDetailRender(Div parent) {
        this.parent = parent;
    }

    @Override
    public void render(Listitem item, TblExportDetail t, int index) throws Exception {
        double price =  t.getPrice();//t.isIsKm() ? 0 :

        item.appendChild(ComponentUtils.createListcell(Integer.toString(index + 1),
                Constants.STYLE_TEXT_ALIGN_CENTER, "col-sm-1", null));
        item.appendChild(ComponentUtils.createListcell(t.getMaSp().getCode()));
        item.appendChild(ComponentUtils.createListcell(t.getMaSp().getName() + ""));
        item.appendChild(ComponentUtils.createListcell(t.getCount() + ""));
        item.appendChild(ComponentUtils.createListcell(t.getMaSp().getUnit()));
        item.appendChild(ComponentUtils.createListcell(Utils.customFormat("###,###.###",
                price)));
        item.appendChild(ComponentUtils.createListcell(Utils.customFormat("###,###.###",
                (t.isIsKm() ? 0 : price * t.getCount()))));
        if(t.isIsKm()) {
            item.setStyle("text-decoration: line-through");
            item.setTooltiptext("Hàng khuyến mãi");
        }
    }
}
