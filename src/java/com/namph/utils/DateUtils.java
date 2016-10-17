/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huy_Nam
 */
public class DateUtils {

    public static Date formatDate(Date input, String strFormat) throws ParseException {
        return convertToDate(convertToString(input, strFormat), strFormat);
    }

    public static String convertToString(Date input, String strFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        return sdf.format(input);
    }

    public static Date convertToDate(String input, String strFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        Date date = sdf.parse(input);
        return date;
    }

    public static Date getCurrent() {
        return new Date();
    }

    public static int getYearCurr() throws ParseException {
//        Calendar cal = Calendar.getInstance();
//        return cal.get(Calendar.YEAR);
        Date date = getTimeVN();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static int getMonthOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    public static int getYearOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static int getMonthCurr() throws ParseException {
        Date date = getTimeVN();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    public static Date getFormattedFromDateTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static Date getFormattedToDateTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static Date getTimeVN() throws ParseException {
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("Asia/Saigon"));
        return convertToDate(df.format(now), "dd/MM/yyyy HH:mm:ss");
    }

    public static Date getTimeVNNoFull() throws ParseException {
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setTimeZone(TimeZone.getTimeZone("Asia/Saigon"));
        return convertToDate(df.format(now), "dd/MM/yyyy");
    }

    public static Date getFirtMonthCurr() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    public static void main(String[] args) {
        try {
            System.out.println(getFirtMonthCurr());
        } catch (Exception e) {
        }
    }

}
