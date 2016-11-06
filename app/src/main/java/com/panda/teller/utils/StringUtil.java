package com.panda.teller.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by root on 16-11-1.
 */

public class StringUtil {

    public static boolean isEmpty(String str) {
        if(str == null || "".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEqual(String str1, String str2) {
        if(str1 == null || str2 == null) {
            return false;
        } else if(!str1.equals(str2)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9][\\w.\\-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w.\\-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z.]*[a-zA-Z]$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }
}
