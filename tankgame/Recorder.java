package com.tank.tankgame;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author D
 * @version 1.0
 * 该类记录相关信息，和文件交互
 */

public class Recorder {

    //定义积分变量
    private static int JF = 0;

    //定义IO对象，追呗写数据到文件中
//    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static String file = "E:\\DH\\d.txt";

    //游戏退出，将积分保存到文件中
    public static void keepJF(){
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(JF + "\r\n");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static int getJF() {
        return JF;
    }

    public static void setJF(int JF) {
        Recorder.JF = JF;
    }

    //我击中一个敌方，就用这个方法
    public static void addJF(){
         Recorder.JF++;
    }

}
