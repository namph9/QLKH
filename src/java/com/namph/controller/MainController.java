/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.controller;

import com.namph.utils.DateUtils;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkmax.zul.Nav;
import org.zkoss.zkmax.zul.Navbar;
import org.zkoss.zkmax.zul.Navitem;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Timer;

/**
 *
 * @author Huy_Nam
 */
public class MainController extends GenericForwardComposer<Div> {

    private Include bodyLayout;
    private Navbar mainMenu;
    private Label account, date;

    @Override
    public void doAfterCompose(Div comp) throws Exception {
        super.doAfterCompose(comp); //To change body of generated methods, choose Tools | Templates.
        Session sess = Sessions.getCurrent();
        if (sess.hasAttribute("login")) {
            initMenu();
            account.setValue("Người dùng: " + sess.getAttribute("login"));
        } else {
            Executions.sendRedirect("login.zul");
        }
    }

    public void onTimer$timer(Event e) {
        try {
            date.setValue(DateUtils.getTimeVN() + "");
        } catch (ParseException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initMenu() {
        Nav nav = new Nav("Bán hàng");
        Navitem item = new Navitem();
        item.setLabel("Bán hàng");
        item.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

            @Override
            public void onEvent(Event t) throws Exception {
                bodyLayout.setSrc("/pages/process/export/index.zul");
            }
        });
        nav.appendChild(item);
        item = new Navitem();
        item.setLabel("Khách hàng");
        item.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

            @Override
            public void onEvent(Event t) throws Exception {
                bodyLayout.setSrc("/pages/categories/agency/index.zul");
            }
        });
        nav.appendChild(item);
        item = new Navitem();
        item.setLabel("Công nợ");
        item.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

            @Override
            public void onEvent(Event t) throws Exception {
                bodyLayout.setSrc("/pages/categories/debit/index.zul");
            }
        });
        nav.appendChild(item);
        item = new Navitem();
        item.setLabel("Thu tiền");
        item.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

            @Override
            public void onEvent(Event t) throws Exception {
                bodyLayout.setSrc("/pages/process/thu/index.zul");
            }
        });
        nav.appendChild(item);
        mainMenu.appendChild(nav);
        nav = new Nav("Nhập hàng");
        item = new Navitem();
        item.setLabel("Nhập hàng");
        item.setIconSclass("fa fa-cloud-download");
        item.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

            @Override
            public void onEvent(Event t) throws Exception {
                bodyLayout.setSrc("/pages/process/import/index.zul");
            }
        });
        nav.appendChild(item);
//        item = new Navitem();
//        item.setLabel("Xuất hàng");
//        item.setIconSclass("fa fa-cloud-upload");
//        item.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
//
//            @Override
//            public void onEvent(Event t) throws Exception {
//                bodyLayout.setSrc("/pages/process/export/index.zul");
//            }
//        });
//        nav.appendChild(item);
        item = new Navitem();
        item.setLabel("Kiểm kê hàng tồn");
        item.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

            @Override
            public void onEvent(Event t) throws Exception {
                bodyLayout.setSrc("/pages/process/tonkho/index.zul");
            }
        });
        nav.appendChild(item);
        mainMenu.appendChild(nav);

        nav = new Nav("Danh mục");
        item = new Navitem();
        item.setLabel("Nhóm hàng hóa");
        item.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

            @Override
            public void onEvent(Event t) throws Exception {
                bodyLayout.setSrc("/pages/categories/p_type/index.zul");
            }
        });
        nav.appendChild(item);
        item = new Navitem();
        item.setLabel("Hàng hóa");
        item.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

            @Override
            public void onEvent(Event t) throws Exception {
                bodyLayout.setSrc("/pages/categories/products/index.zul");
            }
        });
        nav.appendChild(item);
        mainMenu.appendChild(nav);

        bodyLayout.setSrc("dashboard.zul");
    }

}
