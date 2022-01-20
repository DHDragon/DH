package com.tank.tankgame;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

/**
 * @author D
 * @version 1.0
 * 坦克绘图区域
 */

public class MyPanel extends JPanel implements KeyListener, Runnable {

    //定义我的坦克
    Hero hero = null;
    //定义对手，放入集合Vector，考虑多线程因素
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankSize = 3;

    //定义爆炸，存放炸弹
    //子弹击中坦克时加入一个bomb对象到bombs
    Vector<Bomb> bombs = new Vector<>();
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;


    public MyPanel() {
        hero = new Hero(500, 100);//初始化自己的坦克
        //初始化敌方
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            //将 enemyTanks 设置给 enemyTank
            enemyTank.setEnemyTanks(enemyTanks);
            //初始化敌方方向
            enemyTank.setDirect(1);
            //启动敌方坦克线程，让他动起来
            new Thread(enemyTank).start();
            //给敌方加入一颗子弹
            Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
            //把子弹加入敌方的Vector集合中
            enemyTank.shots.add(shot);
            //启动子弹对象
            new Thread(shot).start();
            enemyTanks.add(enemyTank);
        }

        //炸弹图片放入到对象中
        try {
            image1 = ImageIO.read(new FileInputStream("E:\\IdeaProjects\\First\\out\\production\\First\\com\\tank\\bomb.png"));
            image2 = ImageIO.read(new FileInputStream("E:\\IdeaProjects\\First\\out\\production\\First\\com\\tank\\bomb1.png"));
            image3 = ImageIO.read(new FileInputStream("E:\\IdeaProjects\\First\\out\\production\\First\\com\\tank\\bomb2.png"));
            System.out.println("333333333");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //画出积分榜位置和信息
    public void showInfo(Graphics g){
        //画出玩家成绩
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        g.drawString("积分",1020,30);
        drawTank(1020,60,g,0,0);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getJF() + "",1080,100);

    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //填充矩形，默认黑色，定义游戏区域X\Y最大值
        g.fillRect(0, 0, 1000, 750);
        showInfo(g);

        //绘制自己的坦克-封装方法
        if (hero.isLive && hero != null) {
            drawTank(hero.getX(), hero.getY(), g, 0, hero.getDirect());
        }

        //画出hero射击的子弹
//        if (hero.shot != null && hero.shot.isLive == true) {
//            g.draw3DRect(hero.shot.x, hero.shot.y, 1, 1, false);
//        }
        //发射多颗子弹
        for (int i = 0; i < hero.shorts.size(); i++) {
            Shot shot = hero.shorts.get(i);
            if (shot != null && shot.isLive) {
                g.draw3DRect(shot.x, shot.y, 1, 1, false);
            } else {//如果shot对象已经无效，就从shots集合中拿掉
                hero.shorts.remove(shot);
            }
        }

        //如果bombs集合有对象，则画出bomb
        if (bombs.size() != 0) {
            for (int i = 0; i < bombs.size(); i++) {
                Bomb bomb = bombs.get(i);
                //根据life画出不同时间的图片
                if (bomb.life > 6) {
                    g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
                } else if (bomb.life > 3) {
                    g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
                } else {
                    g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
                }

                //让炸弹生命值减少
                bomb.lifeDown();
                if (bomb.life == 0) {
                    bombs.remove(bomb);
                }
            }
        }

        //遍历Vector画出敌方
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = this.enemyTanks.get(i);
            if (enemyTank.isLive) {//敌方存活才显示
                drawTank(enemyTank.getX(), enemyTank.getY(), g, 1, enemyTank.getDirect());
                //画出所有敌方那个的子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //取出子弹
                    Shot shot = enemyTank.shots.get(j);
                    //绘制子弹
                    if (shot.isLive) {
                        g.draw3DRect(shot.x, shot.y, 1, 1, false);
                    } else {
                        System.out.println("线程结束");
                        enemyTank.shots.remove(shot);//越界后子弹结束
                    }
                }
            }
        }


//        drawTank(hero.getX(),hero.getY(),g,1,0);
    }

    /**
     * @param x      坐标
     * @param y
     * @param g      画笔
     * @param type   坦克类型
     * @param direct 方向
     */
    public void drawTank(int x, int y, Graphics g, int type, int direct) {
        //根据类型给坦克上色
        switch (type) {
            case 0:
                g.setColor(Color.CYAN);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }
        //根据方向画坦克
        switch (direct) {
            case 0://向上
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1://向下
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 60, x + 20, y + 20);
                break;
            case 2://向左
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            case 3://向右
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 20, y + 20, x + 60, y + 20);
                break;
            default:
                System.out.println("暂不处理");
        }


    }

    //敌方子弹击中我方
    public void hitHero() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            for (int j = 0; j < enemyTanks.get(i).shots.size(); j++) {
                Shot shot = enemyTanks.get(i).shots.get(j);
                if (hero.isLive && shot.isLive) {
                    hitTank(shot, hero);
                }
            }
        }
    }


    //子弹击中敌方坦克
    //遍历集合中子弹是否击中坦克
    public void hitEnemyTank() {

        //遍历子弹
        for (int j = 0; j < hero.shorts.size(); j++) {
            //取出子弹
            Shot shot = hero.shorts.get(j);
            //判断是否击中敌人
            if (shot != null && hero.shot.isLive) {
                //子弹活着
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(hero.shot, enemyTank);
                }
            }
        }
    }


    //子弹是否击中敌方坦克
    //什么时候判断击中敌方？在run中
    public void hitTank(Shot s, Tank enemyTank) {
        int x = s.x;
        int y = s.y;
        int x1 = enemyTank.getX();
        int y1 = enemyTank.getY();
        switch (enemyTank.getDirect()) {
            case 0:
            case 1:
                if (x < x1 + 40 && x > x1 && y > y1 && y < y1 + 60) {
                    enemyTank.isLive = false;
                    s.isLive = false;
                    //击中后需要将移除掉
                    enemyTanks.remove(enemyTank);

                    //击中敌方坦克加分
                    if (enemyTank instanceof EnemyTank){
                        Recorder.addJF();
                    }
                    //击中坦克生成一个bomb对象
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    //将bomb对象加入到炸弹集合中
                    bombs.add(bomb);
                    break;
                }
            case 2:
            case 3:
                if (x < x1 + 60 && x > x1 && y > y1 && y < y1 + 40) {
                    enemyTank.isLive = false;
                    s.isLive = false;
                    enemyTanks.remove(enemyTank);

                    if (enemyTank instanceof EnemyTank){
                        Recorder.addJF();
                    }

                    //击中坦克生成一个bomb对象
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    //将bomb对象加入到炸弹集合中
                    bombs.add(bomb);
                    break;
                }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_DOWN) {
            hero.setDirect(1);//获取坦克方向
            //驱动坦克
            if (hero.getY() + 110 < 750) {
                hero.moveDown();
            }
        } else if (e.getKeyCode() == e.VK_UP) {
            hero.setDirect(0);
            if (hero.getY() > 0) {
                hero.moveUp();
            }
        } else if (e.getKeyCode() == e.VK_LEFT) {
            hero.setDirect(2);
            if (hero.getX() > 0) {
                hero.moveLeft();
            }
        } else if (e.getKeyCode() == e.VK_RIGHT) {
            hero.setDirect(3);
            if (hero.getX() + 80 < 1000) {
                hero.moveRight();
            }
        }
        //调用shot线程
        if (e.getKeyCode() == e.VK_J) {
            System.out.println("使用J射出子弹");
//            if (hero.shot == null || !hero.shot.isLive) {//子弹结束后再发第二发
//                hero.shotEnemyTank();
//            }
            hero.shotEnemyTank();

        }
        //让画板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {//不停重绘刷新区域，子弹就会移动
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*//判断是否击中敌人
            if (hero.shot != null && hero.shot.isLive) {
                //子弹活着
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(hero.shot, enemyTank);
                }
            }*/


            hitEnemyTank();
            hitHero();
            this.repaint();
        }
    }
}
