package com.yb.common.center.util;

import java.util.Random;

/**
 * @author yebing
 */
public class RandomStringUtils {
    /**
     * 获取指定长度随机数字字符串
     * @param number
     * @return
     */
    public static String getRandomNumStr(int number){
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i=0;i<number;i++){
            int j = random.nextInt(9);
            stringBuffer.append(j);
        }
        return stringBuffer.toString();
    }

    public static String getRandomAlphabetStr(int number){
        StringBuffer stringBuffer = new StringBuffer();
        char[] alphabets = {'A','B','C','D','E','F','G',
                'H','I','J','K','L','M','N',
                'O','P','Q','R','S','T',
                'U','V','W','X','Y','Z',
                'a','b','c','d','e','f','g',
                'h','i','j','k','l','m','n',
                'o','p','q','r','s','t',
                'u','v','w','x','y','z'};
        Random random = new Random();
        for (int i=0;i<number;i++){
            int j = random.nextInt(31);
            stringBuffer.append(alphabets[j]);
        }
        return stringBuffer.toString();
    }

    public static String getRandomAlphabetNumStr(int number){
        StringBuffer stringBuffer = new StringBuffer();
        char[] alphabets = {'A','B','C','D','E','F','G',
                'H','I','J','K','L','M','N',
                'O','P','Q','R','S','T',
                'U','V','W','X','Y','Z',
                'a','b','c','d','e','f','g',
                'h','i','j','k','l','m','n',
                'o','p','q','r','s','t',
                'u','v','w','x','y','z',
                '0','1','2','3','4','5','6','7','8','9'};
        Random random = new Random();
        for (int i=0;i<number;i++){
            int j = random.nextInt(41);
            stringBuffer.append(alphabets[j]);
        }
        return stringBuffer.toString();
    }
}
