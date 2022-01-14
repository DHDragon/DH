package com.tank.file;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author D
 * @version 1.0
 */

public class FileInput_ {
    public static void main(String[] args){

    }

    @Test
    public void inputFile(){
        String E = "E:\\DH\\d.txt";
        //创建FileInputStream对象，用于读取文件
        FileInputStream fileInputStream = null;
        int i = 0;

        try {
            fileInputStream = new FileInputStream(E);
            //从该输入流读取一个字节的数据，如果没有输入可用，此方法将阻止
            //i返回-1表示读取完毕
            while ((i = fileInputStream.read()) != -1){
                System.out.print((char)i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭文件流，释放资源
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
