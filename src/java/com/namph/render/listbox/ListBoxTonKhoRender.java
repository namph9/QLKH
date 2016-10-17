/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.render.listbox;

import com.namph.utils.ComponentUtils;
import com.namph.utils.Constants;
import com.namph.utils.dto.TonKhoDto;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

/**
 *
 * @author Huy_Nam
 */
public class ListBoxTonKhoRender implements ListitemRenderer<TonKhoDto> {

    @Override
    public void render(Listitem item, TonKhoDto t, int index) throws Exception {
        item.appendChild(ComponentUtils.createListcell(Integer.toString(index + 1),
                Constants.STYLE_TEXT_ALIGN_CENTER, "col-xs-1", null));
        item.appendChild(ComponentUtils.createListcell(t.getMsSp()));
        item.appendChild(ComponentUtils.createListcell(t.getTenSp()));
        item.appendChild(ComponentUtils.createListcell(t.getCount() + ""));
        item.appendChild(ComponentUtils.createListcell("KG"));
    }

}
