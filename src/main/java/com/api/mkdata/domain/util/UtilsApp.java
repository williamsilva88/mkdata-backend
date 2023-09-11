package com.api.mkdata.domain.util;

import java.util.regex.Pattern;

public class UtilsApp {

    public static String removerCaractereresCpfCnpj(String value)
    {
        String newStr = value.replaceAll("/","");
        newStr = newStr.replaceAll("-","");
        newStr = newStr.replaceAll(Pattern.quote("."),"");
        newStr = newStr.replaceAll(",","");
        return newStr;
    }

    public static boolean isNumeric(String s)
    {
        if (s == null || s.equals("")) {
            return false;
        }

        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

}
