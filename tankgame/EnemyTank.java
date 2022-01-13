package com.tank.tankgame;

import java.util.Vector;

/**
 * @author D
 * @version 1.0
 */

public class EnemyTank extends Tank implements Runnable {
    //定义shot对象，表示一个射击线程
    Shot shot = null;
    //使用Vector集合，保存多个shot
    Vector<Shot> shots = new Vector<>();
    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    /**
     * 使用Vector集合，保存多个shot
     * 当没创建一个敌方，给地方初始化shot对象，同时启动shot
     * 绘制敌方，遍历Vector对象，绘制所有子弹，当越界时子弹消失
     */
    @Override
    public void run() {
        while (true) {
            //敌方发射多个子弹，并且定位子弹位置，并且启动子弹
            if (isLive && shots.size() < 10) {
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
                shots.add(shot);
                new Thread(shot).start();
            }



            switch (getDirect()) {
                case 0:
                    for (int i = 0; i < 30; i++) {
                        if (getY() > 0) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        if (getY() + 60 < 750) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if (getX() > 0) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if (getX() + 90 < 1000) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            setDirect((int) (Math.random() * 4));//[0,4)随机数
            //tank被击中敌方线程结束一个坦克，多线程时候需要知道什么时候结束线程
            if (!isLive) {
                break;
            }
        }


    }
}
