/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.utils;

import java.text.DecimalFormat;

/**
 *
 * @author Huy_Nam
 */
public class Utils {

    public static Integer convertStr(String input) throws Exception {
        return Integer.parseInt(input);
    }

    public static String formatSoHD(int input) {
        String result = "";
        if (input < 10) {
            result = "0000" + input;
        } else if (input < 100) {
            result = "000" + input;
        } else if (input < 1000) {
            result = "00" + input;
        } else if (input < 10000) {
            result = "0" + input;
        } else {
            result = "" + input;
        }
        return result;
    }

    static public String customFormat(String pattern, double value) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        return output;
    }
}
