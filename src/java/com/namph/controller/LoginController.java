/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.controller;

import com.namph.models.User;
import com.namph.service.UserService;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Div;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

/**
 *
 * @author Huy_Nam
 */
public class LoginController extends GenericForwardComposer<Div> {

    private Textbox id, pass;

    @Override
    public void doAfterCompose(Div comp) throws Exception {
        super.doAfterCompose(comp); //To change body of generated methods, choose Tools | Templates.
        init();
    }

    private void init() {
        Session sess = Sessions.getCurrent();
        if (sess.hasAttribute("login")) {
            Executions.sendRedirect("index.zul");
        }
    }

    public void onClick$btnLogin() {
        User user = ((UserService) SpringUtil.getBean("userService")).
                getUser(id.getValue(), pass.getValue());
        if (null != user) {
            Session sess = Sessions.getCurrent();
            sess.setAttribute("login", id.getValue());
            Executions.sendRedirect("index.zul");
        }else{
             Messagebox.show("Thành công", "Đăng nhập", Messagebox.OK, Messagebox.INFORMATION);
        }
    }
}
