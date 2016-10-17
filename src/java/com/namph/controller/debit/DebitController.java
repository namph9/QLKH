/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.controller.debit;

import com.namph.render.listbox.ListBoxDebitRender;
import com.namph.service.TonKhoService;
import com.namph.utils.dto.TonKhoDto;
import java.util.List;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

/**
 *
 * @author namph
 */
public class DebitController extends GenericForwardComposer<Div> {

    private Listbox tblDebit;
    private Textbox txtAgentCode;

    @Override
    public void doAfterCompose(Div comp) throws Exception {
        super.doAfterCompose(comp);
        init();
    }

    private void init() {
        List<TonKhoDto> lst = ((TonKhoService) SpringUtil.getBean("tonKhoService"))
                .getLstCongno(txtAgentCode.getValue());
        ListModel<TonKhoDto> modelDebit = new ListModelList<TonKhoDto>(lst);
        tblDebit.setModel(modelDebit);
        tblDebit.setItemRenderer(new ListBoxDebitRender());
        tblDebit.setMultiple(true);
    }
    
    public void onClick$btnSearch(){
        init();
    }
}
