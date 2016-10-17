/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.render.combobox;

import com.namph.models.TblProducts;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;

/**
 *
 * @author Huy_Nam
 */
public class ComboProducts implements ComboitemRenderer<TblProducts> {

    @Override
    public void render(Comboitem item, TblProducts t, int i) throws Exception {
        item.setValue(t);
        item.setLabel(t.getName());
        item.setContent("----" + t.getCount());
    }

}
