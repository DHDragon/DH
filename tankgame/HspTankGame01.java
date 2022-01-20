package com.tank.tankgame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * @author D
 * @version 1.0
 */

public class HspTankGame01 extends JFrame {
    //定义MyPanel
    MyPanel mp = null;
    public static void main(String[] args) throws IOException {
        HspTankGame01 hspTankGame01 = new HspTankGame01();
    }
//游戏面板放在这个窗口里
    public HspTankGame01() {
        mp = new MyPanel();
        //将mp放到线程中去启动
        Thread thread = new Thread(mp);
        thread.start();

        this.add(mp);//把面板（游戏区域）
        this.setSize(1300,750);//游戏打开的界面窗口
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //JFrame监听关闭事件
        this.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
            }
        });

        this.repaint();



    }
}
