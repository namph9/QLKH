/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.render.listbox;

import com.namph.models.TblProductType;
import com.namph.utils.ComponentUtils;
import com.namph.utils.Constants;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

/**
 *
 * @author Huy_Nam
 */
public class ListBoxTypeRender implements ListitemRenderer<TblProductType> {

    @Override
    public void render(Listitem item, TblProductType t, int index) throws Exception {
        item.appendChild(ComponentUtils.createListcell(Integer.toString(index + 1),
                Constants.STYLE_TEXT_ALIGN_CENTER, "col-xs-1", null));
        item.appendChild(ComponentUtils.createListcell(t.getCode()));
        item.appendChild(ComponentUtils.createListcell(t.getName()));
    }

}
