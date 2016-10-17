/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.controller;

/**
 *
 * @author Huy_Nam
 */
public class Test {

    public static void main(String[] args) {
//        Date now = new Date(); 
//        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//        df.setTimeZone(TimeZone.getTimeZone("Asia/Saigon"));
//
//        System.out.println("timeZone.......-->>>>>>" + df.format(now));

        long t = -5l;
        System.out.println(t);
        String input = "124,124";
        System.out.println(Double.parseDouble(input.replaceAll(",", "")));
    }
}
