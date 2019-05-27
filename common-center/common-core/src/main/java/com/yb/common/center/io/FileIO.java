package com.yb.common.center.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author yebing
 */
public class FileIO {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public String readFile(String filePath){
        FileInputStream fileInputStream = null;
        FileChannel fileChannel = null;
        StringBuffer stringBuffer = new StringBuffer("");
        try {
            File file = new File(filePath);
            fileInputStream = new FileInputStream(file);

            fileChannel = fileInputStream.getChannel();
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
            try {
                if(fileChannel != null){
                    fileChannel.close();
                }
                if(fileInputStream != null){
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return stringBuffer.toString();
    }

    public void writeFile(String filePath,boolean append){
        FileOutputStream fileOutputStream = null;
        FileChannel fileChannel = null;
        try {
            File file = new File(filePath);
            fileOutputStream = new FileOutputStream(file,append);
            fileChannel = fileOutputStream.getChannel();
            //创建缓冲区
            ByteBuffer writeByteBuffer = ByteBuffer.allocate(1024);
            byte[] bytes = new String("Hello 叶冰!").getBytes();
            writeByteBuffer.put(bytes);
            //从通道中读取数据
            writeByteBuffer.flip();
            fileChannel.write(writeByteBuffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if(fileChannel.isOpen()){
                    fileChannel.close();
                }
                if(fileOutputStream != null){
                    fileOutputStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void methodOne(String str, String match){
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

    public void methodTwo(String str, String match){
        int count= 0;
        int index = 0;
        logger.info("str长度："+str.length());
        while( index >= 0 && index <= str.length()){
            ++count;
            int tmpIndex = str.indexOf(match, index);
            if(index == tmpIndex){
                break;
            }else{
                index = tmpIndex;
            }
        }
        System.out.println(count);
    }
        public static void main(java.lang.String[] args) {
        FileIO fileIO = new FileIO();
        String str = fileIO.readFile("E:/Desktop/test.txt");
        fileIO.methodTwo(str,"叶冰");
        fileIO.writeFile("E:/Desktop/test.txt",true);
    }
}
