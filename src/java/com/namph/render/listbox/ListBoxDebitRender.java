/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.render.listbox;

import com.namph.utils.ComponentUtils;
import com.namph.utils.Constants;
import com.namph.utils.Utils;
import com.namph.utils.dto.TonKhoDto;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

/**
 *
 * @author namph
 */
public class ListBoxDebitRender implements ListitemRenderer<TonKhoDto> {

    @Override
    public void render(Listitem item, TonKhoDto t, int index) throws Exception {
        item.appendChild(ComponentUtils.createListcell(Integer.toString(index + 1),
                Constants.STYLE_TEXT_ALIGN_CENTER, "col-xs-1", null));
        item.appendChild(ComponentUtils.createListcell(t.getCode(),                
                Constants.STYLE_TEXT_ALIGN_CENTER, "col-sm-1", null));
        item.appendChild(ComponentUtils.createListcell(t.getName(),                
                Constants.STYLE_TEXT_ALIGN_CENTER, "col-sm-2", null));
        item.appendChild(ComponentUtils.createListcell(t.getAddress()));
        item.appendChild(ComponentUtils.createListcell(
                Utils.customFormat("###,###.###", t.getCongno()),                
                Constants.STYLE_TEXT_ALIGN_CENTER, "col-sm-1", null));
    }
}
