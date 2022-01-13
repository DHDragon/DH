package com.tank.tankgame;

/**
 * @author D
 * @version 1.0
 * 射击动作
 */

public class Shot implements Runnable{
    int x;
    int y;
    int direct = 0;//子弹方向
    int speed = 2;
    boolean isLive = true;

    public Shot(int x, int y, int direct) {//构造器
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //根据方向来改变子弹坐标
            switch (direct) {
                case 0://上
                    y -= speed;
                    break;
                case 1://下
                    y += speed;
                    break;
                case 2://左
                    x -= speed;
                    break;
                case 3://右
                    x += speed;
                    break;
            }
            //查看输出的坐标
            System.out.println("子弹位置：X="+x+"***"+"Y="+y);
            //子弹到边界就消失
            //击中敌方后子弹结束
            if (!(x <= 1000 && x >= 0 && y >= 0 && y <= 750  && isLive)){
                System.out.println("我方子弹结束");
                isLive = false;
                break;
            }
        }
    }
}
