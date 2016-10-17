/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.render.listbox;

import com.namph.models.Import;
import com.namph.models.TblImportDetail;
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
public class ListBoxImportRender implements ListitemRenderer<Import> {

    private final Div parent;

    public ListBoxImportRender(Div parent) {
        this.parent = parent;
    }

    @Override
    public void render(Listitem item, Import t, int index) throws Exception {
        item.appendChild(ComponentUtils.createListcell(Integer.toString(index + 1),
                Constants.STYLE_TEXT_ALIGN_CENTER, "col-sm-1", null));
        item.appendChild(ComponentUtils.createListcell(DateUtils.convertToString(t.getInsertDate(), "dd/MM/yyyy"),
                Constants.STYLE_TEXT_ALIGN_LEFT, "col-xs-2", null));
        item.appendChild(ComponentUtils.createListcell(t.getStrSoHD(),
                Constants.STYLE_TEXT_ALIGN_LEFT, "col-xs-2", null));
        item.appendChild(ComponentUtils.createListcell(t.getName()));
        List<TblImportDetail> lst = t.getTblImportDetailList();
        double total = 0;
        for (TblImportDetail obj : lst) {
            total += obj.getCount() * obj.getPrice();
        }
        item.appendChild(ComponentUtils.createListcell(Utils.customFormat("###,###.###", total),
                Constants.STYLE_TEXT_ALIGN_LEFT, "col-sm-3", null));
        item.addForward(Events.ON_CLICK, parent, "onShowDetail", t);
    }

}
