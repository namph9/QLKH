/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.namph.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author os_linhlh2
 */
public class Validator {
    public static String getNotNullString(Object object) {
        return object != null ? object.toString() : StringPool.BLANK;
    }

    /**
     * Returns
     * <code>true</code> if the booleans are equal.
     *
     * @param boolean1 the first boolean
     * @param boolean2 the second boolean
     * @return <code>true</code> if the booleans are equal; <code>false</code>
     * otherwise
     */
    public static boolean equals(boolean boolean1, boolean boolean2) {
        if (boolean1 == boolean2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns
     * <code>true</code> if the bytes are equal.
     *
     * @param byte1 the first byte
     * @param byte2 the second byte
     * @return <code>true</code> if the bytes are equal; <code>false</code>
     * otherwise
     */
    public static boolean equals(byte byte1, byte byte2) {
        if (byte1 == byte2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns
     * <code>true</code> if the characters are equal.
     *
     * @param char1 the first character
     * @param char2 the second character
     * @return <code>true</code> if the characters are equal; <code>false</code>
     * otherwise
     */
    public static boolean equals(char char1, char char2) {
        if (char1 == char2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns
     * <code>true</code> if the doubles are equal.
     *
     * @param double1 the first double
     * @param double2 the second double
     * @return <code>true</code> if the doubles are equal; <code>false</code>
     * otherwise
     */
    public static boolean equals(double double1, double double2) {
        if (Double.compare(double1, double2) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns
     * <code>true</code> if the floats are equal.
     *
     * @param float1 the first float
     * @param float2 the second float
     * @return <code>true</code> if the floats are equal; <code>false</code>
     * otherwise
     */
    public static boolean equals(float float1, float float2) {
        if (Float.compare(float1, float2) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns
     * <code>true</code> if the integers are equal.
     *
     * @param int1 the first integer
     * @param int2 the second integer
     * @return <code>true</code> if the integers are equal; <code>false</code>
     * otherwise
     */
    public static boolean equals(int int1, int int2) {
        if (int1 == int2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns
     * <code>true</code> if the long integers are equal.
     *
     * @param long1 the first long integer
     * @param long2 the second long integer
     * @return <code>true</code> if the long integers are equal;
     * <code>false</code> otherwise
     */
    public static boolean equals(long long1, long long2) {
        if (long1 == long2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns
     * <code>true</code> if the objects are either equal, the same instance, or
     * both
     * <code>null</code>.
     *
     * @param obj1 the first object
     * @param obj2 the second object
     * @return <code>true</code> if the objects are either equal, the same
     * instance, or both <code>null</code>; <code>false</code> otherwise
     */
    public static boolean equals(Object obj1, Object obj2) {
        if ((obj1 == null) && (obj2 == null)) {
            return true;
        } else if ((obj1 == null) || (obj2 == null)) {
            return false;
        } else {
            return obj1.equals(obj2);
        }
    }

    /**
     * Returns
     * <code>true</code> if the short integers are equal.
     *
     * @param short1 the first short integer
     * @param short2 the second short integer
     * @return <code>true</code> if the short integers are equal;
     * <code>false</code> otherwise
     */
    public static boolean equals(short short1, short short2) {
        if (short1 == short2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns
     * <code>true</code> if the short integers are equal.
     *
     * @param date1 the first Date
     * @param date2 the second Date
     * @return <code>true</code> if the dates are equal; <code>false</code>
     * otherwise
     */
    public static boolean equals(Date date1, Date date2) {
        if(date1 == null && date2 != null){
            return false;
        }
        if(date1 != null && date2 == null){
            return false;
        }
        if(date1 == null && date2 == null){
            return true;
        }
        
        if (date1.compareTo(date2) == 0) {
            return true;
        } else {
            return false;
        }
    }



    /**
     * Returns
     * <code>true</code> if the character is in the ASCII character set. This
     * includes characters with integer values between 32 and 126 (inclusive).
     *
     * @param c the character to check
     * @return <code>true</code> if the character is in the ASCII character set;
     * <code>false</code> otherwise
     */
    public static boolean isAscii(char c) {
        int i = c;

        if ((i >= 32) && (i <= 126)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns
     * <code>true</code> if the character is an upper or lower case English
     * letter.
     *
     * @param c the character to check
     * @return <code>true</code> if the character is an upper or lower case
     * English letter; <code>false</code> otherwise
     */
    public static boolean isChar(char c) {
        int x = c;

        if ((x >= _CHAR_BEGIN) && (x <= _CHAR_END)) {
            return true;
        }

        return false;
    }

    /**
     * Returns
     * <code>true</code> if the character is a digit between 0 and 9
     * (inclusive).
     *
     * @param c the character to check
     * @return <code>true</code> if the character is a digit between 0 and 9
     * (inclusive); <code>false</code> otherwise
     */
    public static boolean isDigit(char c) {
        int x = c;

        if ((x >= _DIGIT_BEGIN) && (x <= _DIGIT_END)) {
            return true;
        }

        return false;
    }


    public static String getSafeFileName(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != '/' && c != '\\' && c != 0) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Returns
     * <code>true</code> if the string is a valid variable term, meaning it
     * begins with "[$" and ends with "$]".
     *
     * @param s the string to check
     * @return <code>true</code> if the string is a valid variable term;
     * <code>false</code> otherwise
     */
    public static boolean isVariableTerm(String s) {
        if (s.startsWith(_VARIABLE_TERM_BEGIN)
                && s.endsWith(_VARIABLE_TERM_END)) {

            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns
     * <code>true</code> if the character is whitespace, meaning it is either
     * the
     * <code>null</code> character '0' or whitespace according to
     * {@link java.lang.Character#isWhitespace(char)}.
     *
     * @param c the character to check
     * @return <code>true</code> if the character is whitespace;
     * <code>false</code> otherwise
     */
    public static boolean isWhitespace(char c) {
        int i = c;

        if ((i == 0) || Character.isWhitespace(c)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns
     * <code>true</code> if the string is an XML document. The only requirement
     * is that it contain either the xml start tag "<?xml" or the empty document
     * tag "<root />".
     *
     * @param s the string to check
     * @return <code>true</code> if the string is an XML document;
     * <code>false</code> otherwise
     */
    public static boolean isXml(String s) {
        if (s.startsWith(_XML_BEGIN) || s.startsWith(_XML_EMPTY)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean hasSpecialWord(String value, String regex) {
        boolean test = false;
        try {
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
            Matcher m = p.matcher(value);
            if (m.find()) {
                test = true;
            }
        } catch (Exception e) {
        }

        return test;
    }

    public static boolean checkRegex(String value, String regex) {
        boolean test = false;
        try {
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
            Matcher m = p.matcher(value);
            test = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }
    // vsm_manhnn1_start_new

    public static boolean isVersion(String input) {
        if (input != null && input.matches(_version)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPort(String input) {
        if (input != null && input.matches(_port)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isHour(String input) {
        if (input != null && input.matches(_hour)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isMinute(String input) {
        if (input != null && input.matches(_minute)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDate(String input) {
        Date date = null;
        if (null == input) {
            return false;
        }
        for (SimpleDateFormat format : dateFormats) {
            try {
                format.setLenient(false);
                date = format.parse(input);
            } catch (Exception ex) {
                //Shhh.. try other formats
            }
            if (date != null) {
                break;

            }
        }
        if (date != null) {
            return true;
        } else {
            return false;
        }
    }
    // </end>
    private static List<SimpleDateFormat> dateFormats = new ArrayList<SimpleDateFormat>() {
        {
            add(new SimpleDateFormat("MM/dd/yyyy"));
            add(new SimpleDateFormat("dd.MM.yyyy"));
            add(new SimpleDateFormat("dd/MM/yyyy"));
            add(new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a"));
            add(new SimpleDateFormat("dd.MM.yyyy hh:mm:ss a"));
            add(new SimpleDateFormat("dd.MMM.yyyy"));
            add(new SimpleDateFormat("dd-MMM-yyyy"));
        }
    };
    private static final int _CHAR_BEGIN = 65;
    private static final int _CHAR_END = 122;
    private static final int _DIGIT_BEGIN = 48;
    private static final int _DIGIT_END = 57;
    private static final char[] _EMAIL_ADDRESS_SPECIAL_CHAR = new char[]{
        '.', '!', '#', '$', '%', '&', '\'', '*', '+', '-', '/', '=', '?', '^',
        '_', '`', '{', '|', '}', '~'
    };
    private static final String _LOCALHOST = "localhost";
    private static final String _VARIABLE_TERM_BEGIN = "[$";
    private static final String _VARIABLE_TERM_END = "$]";
    private static final String _XML_BEGIN = "<?xml";
    private static final String _XML_EMPTY = "<root />";
    private static Pattern _emailAddressPattern = Pattern.compile(
            "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?");
    private static Pattern _ipAddressPattern = Pattern.compile(
            "\\b"
            + "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\."
            + "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\."
            + "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\."
            + "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])"
            + "\\b");
    private static Pattern _variableNamePattern = Pattern.compile(
            "[_a-zA-Z]+[_a-zA-Z0-9]*");
    private static Pattern _codeStringPattern = Pattern.compile(
            "[_a-zA-Z0-9]*");
    private static Pattern _numberPattern = Pattern.compile(
            "[+-]?\\d*(\\.\\d+)?");
    // vsm_manhnn1_start_new
    private static String _version = "^(\\d+\\.){1,3}(\\d+)";
    private static String _port = "^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$";
    private static String _hour = "([01]?[0-9]|2[0-3])";
    private static String _minute = "[0-5][0-9]";
    // </end>
}
