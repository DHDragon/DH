package com.tank.threaduse;

/**
 * @author D
 * @version 1.0
 */

public class threadUse {
    public static void main(String[] args){
        Cat cat = new Cat();
        cat.start();//启动线程
        cat.run();//这调用的就是main调用的一个普通方法
    }
}
class Cat extends Thread {//继承Thread就是一个线程
    int times = 0;
    @Override
    public void run(){
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("猫叫"+(++times));
            if (times == 8){
                break;
            }
        }

    }
}
