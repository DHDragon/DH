package com.tank.tankgame;

import java.util.Vector;

/**
 * @author D
 * @version 1.0
 */

public class Hero extends Tank{
    //定义shot对象，标是一个射击线程
    Shot shot = null;
    //发射多个子弹
    Vector<Shot> shorts = new Vector<>();
    //自己的坦克
    public Hero(int x, int y) {
        super(x, y);
    }

    //射击
    public void shotEnemyTank() {
        if (shorts.size() == 5) {
            return;
        }
        //创建shot对象，根据坦克方向和坐标，创建子弹shot坐标
        switch (getDirect()) {
            case 0://上
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1://下
                shot = new Shot(getX() + 20, getY() + 60, 1);
                break;
            case 2://左
                shot = new Shot(getX(), getY() + 20, 2);
                break;
            case 3://右
                shot = new Shot(getX() + 60, getY() + 20, 3);
                break;
        }
        //将子弹放到集合中
        shorts.add(shot);
        //启动shot线程
        new Thread(shot).start();

    }
}
