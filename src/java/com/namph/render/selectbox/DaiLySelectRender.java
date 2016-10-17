/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.render.selectbox;

import com.namph.models.TblDaily;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.ItemRenderer;

/**
 * Oct 9, 2016
 *
 * @author Pham_Huy_Nam
 */
public class DaiLySelectRender implements ItemRenderer<TblDaily> {

    @Override
    public String render(Component cmpnt, TblDaily t, int i) throws Exception {
        cmpnt.setAttribute("code", t.getCode());
        cmpnt.setAttribute("address", t.getAddress());
        return t.getName();
    }

}
