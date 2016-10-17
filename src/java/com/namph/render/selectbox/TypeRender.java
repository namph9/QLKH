/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.render.selectbox;

import com.namph.models.TblProductType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.ItemRenderer;

/**
 *
 * @author Huy_Nam
 */
public class TypeRender implements ItemRenderer<TblProductType> {

    @Override
    public String render(Component item, TblProductType t, int i) throws Exception {
        return t.getName();
    }

}
