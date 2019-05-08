package com.yb.common.center.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 日期工具类
 * @author yebing
 */
public class DateUtil {
    public static final String YYMMDD = "yyyyMMdd";
    public static final String YYDDMMHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YMD_SLASH = "yyyy/MM/dd";
    public static final String YMD_DASH = "yyyy-MM-dd";
    public static final String YMD_DASH_WITH_TIME = "yyyy-MM-dd H:m";
    public static final String YDM_SLASH = "yyyy/dd/MM";
    public static final String YDM_DASH = "yyyy-dd-MM";
    public static final String HM = "HHmm";
    public static final String HM_COLON = "HH:mm";
    public static final String YMDHMS = "yyyyMMddHHmmss";
    public static Map<String,SimpleDateFormat> simpleDateFormatMap;

    /**
     * 获取指定格式SimpleDateFormat
     * @param pattern
     * @return
     */
    public static SimpleDateFormat getFormat(String pattern) {
        if (simpleDateFormatMap.get(pattern) == null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            simpleDateFormatMap.put(pattern,simpleDateFormat);
        }
        return simpleDateFormatMap.get(pattern);
    };

    /**
     * 获取指定格式的时间字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String getFormatString(Date date, String pattern){
        SimpleDateFormat format = getFormat(pattern);
        return format.format(date);
    }

    /**
     * 获取指定格式的日期
     * @param date
     * @param pattern
     * @return
     */
    public static Date getFormatDate(String date,String pattern){
        SimpleDateFormat format = getFormat(pattern);
        Date parse;
        try {
            parse = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return parse;
    }

}
