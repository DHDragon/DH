package com.tank.tankgame;

/**
 * @author D
 * @version 1.0
 *
 * 炸弹
 */

public class Bomb {
    int x, y;
    boolean isLive = true;
    int life = 9 ;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //减少生命值
    public void lifeDown(){//配合图片炸弹效果
        if(life > 0){
            life--;
        }else {
            isLive = false;
        }
    }
}
