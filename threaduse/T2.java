package com.tank.threaduse;

/**
 * @author D
 * @version 1.0
 */

public class T2 {
    public static void main(String[] args){
        rr rr = new rr();
        Thread thread = new Thread(rr);
        thread.start();
    }
}

class ThreadProxy implements Runnable{//把Proxy当作thread类

    private Runnable target = null;//属性，类型是Runnable
    @Override
    public void run() {
        if (target != null){
            target.run();
        }
    }

    public ThreadProxy(Runnable target) {
        this.target = target;
    }

    public void start(){
        start0();//真正开启线程的地方
    }

    public void start0(){
        run();
    }
}

class rr implements Runnable{//实现Runnable
    int time=0;
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hi"+(++time)+Thread.currentThread().getName());
            if (time == 10){
                break;
            }
        }
    }
}
