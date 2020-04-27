package com.oliwen.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static final Pattern PATTERN_NUMBER = Pattern.compile("([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])");

    public static final Pattern PATTERN_UNICODE = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");

    public static String replaceOther(String str){
        if(str != null && !"".equals(str)){
            return  str.replaceAll( "[\\pP+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]" , "").replaceAll(" ","");
        }
        return null;
    }

    public static String getUUID(){
        return  UUID.randomUUID().toString().replace("-", "");
    }

    public static boolean isEmpty(String str) {
        if (str != null && !"".equals(str) && !"null".equals(str) && !"undefined".equals(str) && str.trim().length() > 0) {
            return false;
        }
        return true;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isEmpty(Object str) {
        if (str == null){
            return true;
        }
        return !isNotEmpty(str);
    }

    public static boolean isNotEmpty(Object str) {
        if (str == null){
            return false;
        }
        return !isEmpty(str.toString());
    }

    public static String unicodeToString(String str) {

        Matcher matcher = PATTERN_UNICODE.matcher(str);
        char ch;
        while (matcher.find()) {
            //group 6728
            String group = matcher.group(2);
            //ch:'木' 26408
            ch = (char) Integer.parseInt(group, 16);
            //group1 \u6728
            String group1 = matcher.group(1);
            str = str.replace(group1, ch + "");
        }
        return str;
    }

    public static String getStringByPrefixAndSuffix(Object obj, String prefix, String suffix) {
        if (obj != null) {
            String content = obj.toString();
            Pattern pattern = Pattern.compile((prefix + "(.*?)" + suffix));
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                try {
                    return matcher.group(0).replaceAll(prefix, "").replaceAll(suffix, "").trim();
                } catch (Exception e) {
                }
            }
        }
        return "";
    }

    public static double getSixDecimal(double num){
        return new BigDecimal(num).setScale(6,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static boolean isNumber(String orginal){
        if (orginal == null || orginal.trim().equals("")) {
            return false;
        }
        Matcher isNum = PATTERN_NUMBER.matcher(orginal);
        return isNum.matches();
    }

    public static Integer getInteger(String str){
        return StringUtil.isEmpty(str) ? null : Integer.parseInt(str);
    }

    public static String longToStr(Long data){
        return data == null ? null : data.toString();
    }


    public static String formatOnePoint(Object obj){
        if(obj == null || !isNumber(obj.toString())){
           return "0";
        }
        DecimalFormat df = new DecimalFormat("##0.0");
        return df.format(obj);
    }
}
