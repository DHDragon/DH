package com.tank.threaduse;

/**
 * @author D
 * @version 1.0
 */

public class threadMethod02 {
    public static void main(String[] args) throws InterruptedException {
        T22 t22 = new T22();
        t22.start();
        for (int i = 0; i <= 20; i++) {
            Thread.sleep(50);
            System.out.println("线程"+Thread.currentThread().getName()+"------在吃第" + i + "包子------");
            if (i == 5){
                t22.join();//相当于t22线程先执行，线程插队
                Thread.yield();
            }
        }

    }
}

class T22 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i <= 20; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("------在吃第" + i + "包子------");
        }
    }
}
