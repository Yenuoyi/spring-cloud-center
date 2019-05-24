package com.yb.common.center.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author yebing
 */
public class FileIO {
    public static String readFile(String filePath){
        FileInputStream fileInputStream = null;
        StringBuffer stringBuffer = new StringBuffer("");
        try {
            File file = new File(filePath);
            fileInputStream = new FileInputStream(file);

            FileChannel fileChannel = fileInputStream.getChannel();
            //创建缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            boolean ok = true;
            //从通道中读取数据
            while (fileChannel.read(byteBuffer) != -1)
            {
                //转换为读数据模式
                byteBuffer.flip();
                Charset charset = Charset.forName("UTF-8");
                stringBuffer.append(charset.decode(byteBuffer).toString());
                //清空缓冲区
                byteBuffer.clear();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuffer.toString();
    }

    public static void method1(String str, String match){
        int nameNum = 0;
        String name = "叶冰";
        int nameIndex = 0;
        for(int i=0;i<str.length();i++){
            char strC = str.charAt(i);
            if(strC == name.charAt(nameIndex)){
                ++nameIndex;
            }else{
                nameIndex = 0;
            }
            if(nameIndex >= name.length()){
                nameIndex = 0;
                ++nameNum;
            }
        }
        System.out.println(nameNum);
    }

    public static void method2(String str, String match){
        int count= 0;
        int index = 0;
        System.out.println(str.length());
        while( index >= 0 && index <= str.length()){
            ++count;
            index = str.indexOf(match,index);
        }
        System.out.println(count);
    }
        public static void main(java.lang.String[] args) {
        String str = readFile("E:/Desktop/test.txt");
        /*method2(str,"叶冰");*/
        System.out.println(str.indexOf("叶冰",24));
    }
}
