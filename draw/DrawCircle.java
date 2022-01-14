package com.tank.draw;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @author D
 * @version 1.0
 * 面板画圆
 */
@SuppressWarnings({"all"})//异常去除
public class DrawCircle extends JFrame{//JFrame对应窗口
    //定义一个面板
    private MyPanle mp = null;
    public static void main(String[] args){
        new DrawCircle();

    }

    public DrawCircle() {//构造器
        //初始化面板
        mp = new MyPanle();
        //把面板放入到窗口
        this.add(mp);
        //设置窗口大小
        this.setSize(800,800);
        //当点击X程序直接退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);//显示画板
    }
}


//继承JPanel类，画圆方法
class MyPanle extends JPanel {
    //1.MyPanle对象就是画板
    //2.Graphics 把g就是画笔
    //3.Graphics提供多种绘图方法
    @Override
    public void paint(Graphics g) {//绘图方法
        super.paint(g);//调用父类完成初始化
        //画一个圆
//        g.drawOval(10,10,100,100);
        //演示绘制不同的图形
        //画图片，/12.png表示在该项目根目录下获取12.png图片资源
        URL resource = Panel.class.getResource("/com/tank/bomb.png");
//        System.out.println(resource+"-------------");
        Image image = Toolkit.getDefaultToolkit().getImage(resource);
//        System.out.println("22222222222222");
        g.drawImage(image,10,10,200,200,this);

    }
}
