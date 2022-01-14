package com.tank.threaduse;

/**
 * @author D
 * @version 1.0
 */

public class DeadLock {
    public static void main(String[] args){
        //模拟死锁
        Th A = new Th(true);
        A.setName("线程A");
        Th B = new Th(false);
        B.setName("线程B");
        A.start();
        B.start();

    }
}

class Th extends Thread{
    static Object o1 = new Object();//static保证多线程，共享一个对象
    static Object o2 = new Object();
    boolean fool;

    public Th(boolean fool) {
        this.fool = fool;
    }

    @Override
    public void run() {
        if (fool){
            synchronized (o1){
                System.out.println(Thread.currentThread().getName()+"进入1");
                synchronized (o2){
                    System.out.println(Thread.currentThread().getName()+"进入2");
                }
            }
        }else {
            synchronized (o2){
                System.out.println(Thread.currentThread().getName()+"进入3");
                synchronized (o1){
                    System.out.println(Thread.currentThread().getName()+"进入4");
                }
            }
        }
    }
}
