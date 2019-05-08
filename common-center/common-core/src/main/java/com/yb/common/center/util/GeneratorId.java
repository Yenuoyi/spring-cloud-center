package com.yb.common.center.util;

import org.springframework.util.StringUtils;

import java.text.NumberFormat;
import java.util.Date;

/**
 * @author yebing
 */
public class GeneratorId {
    /**
     * 获取前缀+长度的字符串编码
     * @param prefix
     * @param numberLength
     * @return
     */
    public static String getSequenceByPrefixAndLength(String prefix , Integer numberLength){
        //不填编码前缀时默认+V
        String cardPrefix = StringUtils.isEmpty(prefix) ? "V" : prefix;
        //编码长度
        Integer strNumberLength = numberLength == null ? 16 : numberLength;
        StringBuilder resultCode = new StringBuilder();
        String currentDate = DateUtil.getFormatString(new Date(),DateUtil.YYMMDD);
        String timeMillis = String.valueOf(System.currentTimeMillis());
        String lastTimeStamp = timeMillis.substring(timeMillis.length() - 4, timeMillis.length());
        String randomNumStr = RandomStringUtils.getRandomNumStr(4);
        resultCode.append(currentDate).append(lastTimeStamp).append(randomNumStr);
        NumberFormat nf = NumberFormat.getInstance();
        //取消分组
        nf.setGroupingUsed(false);
        //设置最小整数位数
        nf.setMinimumIntegerDigits(strNumberLength);
        //最终得出编码
        return cardPrefix + nf.format(Long.valueOf(resultCode.toString()));
    }

    /**
     * 获取前缀+长度的字符串编码
     * @param prefix
     * @param numberLength
     * @return
     */
    public static String getSequenceByPrefixAndLength(Integer numberLength){
        //编码长度
        Integer strNumberLength = numberLength == null ? 16 : numberLength;
        StringBuilder resultCode = new StringBuilder();
        String currentDate = DateUtil.getFormatString(new Date(),DateUtil.YYMMDD);
        String timeMillis = String.valueOf(System.currentTimeMillis());
        String lastTimeStamp = timeMillis.substring(timeMillis.length() - 4, timeMillis.length());
        String randomNumStr = RandomStringUtils.getRandomNumStr(4);
        resultCode.append(currentDate).append(lastTimeStamp).append(randomNumStr);
        NumberFormat nf = NumberFormat.getInstance();
        //取消分组
        nf.setGroupingUsed(false);
        //设置最小整数位数
        nf.setMinimumIntegerDigits(strNumberLength);
        //最终得出编码
        return nf.format(Long.valueOf(resultCode.toString()));
    }
}
