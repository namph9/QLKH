/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.render.listbox;

import com.namph.models.TblExport;
import com.namph.models.TblExportDetail;
import com.namph.utils.ComponentUtils;
import com.namph.utils.Constants;
import com.namph.utils.DateUtils;
import com.namph.utils.Utils;
import java.util.List;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

/**
 *
 * @author Huy_Nam
 */
public class ListBoxExportRender implements ListitemRenderer<TblExport> {

    private final Div parent;

    public ListBoxExportRender(Div parent) {
        this.parent = parent;
    }

    @Override
    public void render(Listitem item, TblExport t, int index) throws Exception {
        item.appendChild(ComponentUtils.createListcell(Integer.toString(index + 1),
                Constants.STYLE_TEXT_ALIGN_CENTER, "col-xs-1", null));
        item.appendChild(ComponentUtils.createListcell(DateUtils.convertToString(t.getInsertDate(), "dd/MM/yyyy"),
                Constants.STYLE_TEXT_ALIGN_LEFT, "col-xs-2", null));
        item.appendChild(ComponentUtils.createListcell(t.getStrSoHD(),
                Constants.STYLE_TEXT_ALIGN_LEFT, "col-xs-2", null));
        item.appendChild(ComponentUtils.createListcell(t.getName().getName()));
        List<TblExportDetail> lst = t.getTblExportDetailList();
        double total = 0;
        for (TblExportDetail obj : lst) {
            if (!obj.isIsKm()) {
                total += obj.getCount() * (obj.getPrice() == null ? 0 : obj.getPrice());
            }
        }
        item.appendChild(ComponentUtils.createListcell(Utils.customFormat("###,###.###", total),
                Constants.STYLE_TEXT_ALIGN_LEFT, "col-sm-3", null));
        item.addForward(Events.ON_CLICK, parent, "onShowDetail", t);
        item.addForward(Events.ON_RIGHT_CLICK, parent, "onDeleteExport", t);
    }

}
