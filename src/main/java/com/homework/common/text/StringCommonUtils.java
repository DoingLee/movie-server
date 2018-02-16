package com.homework.common.text;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCommonUtils {

    public static String[] parseStandardStringArrayFromString(String text) {
        if (org.apache.commons.lang3.StringUtils.isBlank(text)) {
            return null;
        }

        int left = text.indexOf("[");
        int right = text.lastIndexOf("]");

        if (left >= 0 && right > left) {
            String strNoThresh = text.substring(left + 1, right);
            return org.apache.commons.lang3.StringUtils.split(strNoThresh, ",");
        }

        return null;

    }

    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
     *
     * @param s 需要得到长度的字符串
     * @return int 得到的字符串长度
     */
    public static int length(String s) {
        if (s == null)
            return 0;
        char[] c = s.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
        }
        return len;
    }

    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }

    public static String parseColumnNameToPropertyString(String text) {
        if (text == null || text.length() == 0) {
            return text;
        }
        String[] subStrings = text.split("_");
        StringBuilder sb = new StringBuilder();
        for (String subString : subStrings) {
            if (Character.isLowerCase(subString.charAt(0)) && subString.length() > 0) {
                subString = Character.toUpperCase(subString.charAt(0)) + subString.substring(1);
            }
            sb.append(subString);
        }
        return sb.toString();
    }

    public static Long[] parseStandardLongArrayFromString(String text) {
        String[] strAry = parseStandardStringArrayFromString(text);
        if (strAry == null) {
            return null;
        }
        Long[] longAry = new Long[strAry.length];
        for (int i = 0; i < strAry.length; i++) {
            longAry[i] = Long.parseLong(strAry[i]);

        }
        return longAry;

    }

    public static String substring(String s, int maxLength) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(s) || maxLength <= 0) {
            return null;
        }
        maxLength = maxLength > s.length() ? s.length() : maxLength;
        return s.substring(0, maxLength);
    }

    public static String upperFirstChar(String source) {
        if (org.apache.commons.lang3.StringUtils.isBlank(source)) {
            return "";
        }
        Character firstChar = source.charAt(0);
        if (Character.isLowerCase(firstChar)) {
            return Character.toUpperCase(firstChar) + source.substring(1);
        }
        return source;
    }

    public static String convertCamel2UnderlineStyle(String str) {
        if (org.apache.commons.lang3.StringUtils.isBlank(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length() + 10);
        for (int i = 0; i < str.length(); i++) {
            char array_element = str.charAt(i);
            if (Character.isUpperCase(array_element)) {
                if (i != 0) {
                    sb.append("_");
                }
                sb.append(Character.toLowerCase(array_element));
            } else {
                sb.append(array_element);
            }

        }
        return sb.toString();
    }

    /*
     * 正则替换符合规则的符号
     * @param content 要替换的内容
     * @param regular 要替换的正则表达式
     * @param replace 将正则表达式匹配部分替换成什么
     */
    public static String replaceAllRegular(String content, String regular, String replace) {
        String result = "";
        if (content != null) {
            Pattern p = Pattern.compile(regular);
            Matcher m = p.matcher(content);
            result = m.replaceAll(replace);
        }
        return result;
    }

    /*
     * 去除多余空行
     */
    public static String removeMutiCRLF(String content) {
        String onlyLF = replaceAllRegular(content, "\r|\n", "\n");
        return replaceAllRegular(onlyLF, "\n+", "\n");
    }

}
