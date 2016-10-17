/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.utils;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;

/**
 *
 * @author Huy_Nam
 */
public class ComponentUtils {

    public static Listcell createListcell(String label) {
        Listcell cell = new Listcell();
        cell.appendChild(new Label(label));
        return cell;
    }

    public static Listcell createListcell(String label, String style) {
        Listcell cell = new Listcell(label);
        cell.setStyle(style);
        return cell;
    }

    public static Listcell createListcell(String label, String tooltip, String style) {
        Listcell cell = new Listcell();
        Label label1 = new Label(label);
        label1.setTooltiptext(tooltip);
        label1.setStyle(style);
        cell.appendChild(label1);
        return cell;
    }

    public static Listcell createListcell(String label, String style, String classCss, String tooltip) {
        Listcell cell = new Listcell();
        cell.setSclass(classCss);
        Label label1 = new Label(label);
        label1.setTooltiptext(tooltip);
        label1.setStyle(style);
        cell.appendChild(label1);
        return cell;
    }

    public static Button createButton(String label, String iconCls) {
        Button button = new Button();
        button.setStyle("border:none;");
        button.setIconSclass(iconCls);
        button.setSclass("btn btn-default");
        return button;
    }

    public static Button createButton(String label, String iconSclass,
            String sclass) {
        Button button = new Button();
        button.setStyle("border:none");
        button.setIconSclass(iconSclass);
        button.setSclass(sclass);
        button.setTooltiptext(label);

        return button;
    }

    public static Button createButton(Component parent, String label, String toolTipId,
            String event, String eventForward, Object data) {
        Button button = new Button();

        button.addForward(event, parent, eventForward, data);

        return button;
    }

    public static Button createButton(Component parent, String label, String toolTipId,
            String event, String eventForward, Object data, String iconSclass,
            String color) {
        Button button = createButton(parent, label, toolTipId, event, eventForward,
                data);

        button.setIconSclass(iconSclass);
        button.setStyle("border:none;color: " + color);
        return button;
    }
}
