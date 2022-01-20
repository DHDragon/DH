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
    //增加成员，EnemyTank 可以得到敌人坦克的Vector
    //Vector<EnemyTank>在MyPanel中
    Vector<EnemyTank> enemyTanks = new Vector<>();

    /**
     * @param enemyTanks 提供一个方法，将MyPanel中的成员 Vector<EnemyTank> enemyTanks = new Vector<>();
     *                   设置到EnemyTank 的成员 enemyTanks
     */
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    //判断当前坦克是否和其他坦克重叠
    public boolean isTouchEnemyTank() {
        //判断当前（this）敌方坦克的方向
        switch (this.getDirect()) {
            case 0:
                //让当前敌方坦克和所有敌方坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //取出一个敌方坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        //上/下
                        /**
                         *1.找到X\Y范围
                         * 2.在这范围内的话就是碰撞了
                         */
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 1) {
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //左/右
                        if (enemyTank.getDirect() == 2 || enemyTank.getDirect() == 3) {
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //取出一个敌方坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        //上/下
                        /**
                         *1.找到X\Y范围
                         * 2.在这范围内的话就是碰撞了
                         */
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 1) {
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //左/右
                        if (enemyTank.getDirect() == 2 || enemyTank.getDirect() == 3) {
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2:
                //让当前敌方坦克和所有敌方坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //取出一个敌方坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        //上/下
                        /**
                         *1.找到X\Y范围
                         * 2.在这范围内的话就是碰撞了
                         */
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 1) {//敌方坦克上下
                            if (this.getX()  >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //左/右
                        if (enemyTank.getDirect() == 2 || enemyTank.getDirect() == 3) {//敌方坦克左右
                            //当前坦克角
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3:
                //让当前敌方坦克和所有敌方坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //取出一个敌方坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        //上/下
                        /**
                         *1.找到X\Y范围
                         * 2.在这范围内的话就是碰撞了
                         */
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 1) {//敌方坦克上下
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //左/右
                        if (enemyTank.getDirect() == 2 || enemyTank.getDirect() == 3) {//敌方坦克左右
                            //当前坦克右上角
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

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

            //判断不越界，不重叠
            switch (getDirect()) {
                case 0:
                    for (int i = 0; i < 30; i++) {
                        if (getY() > 0 && !isTouchEnemyTank()) {
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
                        if (getY() + 60 < 750 && !isTouchEnemyTank()) {
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
                        if (getX() > 0 && !isTouchEnemyTank()) {
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
                        if (getX() + 90 < 1000 && !isTouchEnemyTank()) {
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
