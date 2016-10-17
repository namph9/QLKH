/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.render.combobox;

import com.namph.models.TblDaily;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;

/**
 * Sep 25, 2016
 *
 * @author Pham_Huy_Nam
 */
public class DaiLyRender implements ComboitemRenderer<TblDaily> {

    @Override
    public void render(Comboitem item, TblDaily t, int i) throws Exception {
        item.setLabel(t.getName());
        item.setValue(t.getCode());
        item.setContext(t.getAddress());
    }

}
