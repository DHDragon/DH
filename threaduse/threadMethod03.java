package com.tank.threaduse;

import java.util.Scanner;

/**
 * @author D
 * @version 1.0
 */

public class threadMethod03 {
    public static void main(String[] args) {
        A a = new A();
        B b = new B(a);
        a.start();
        b.start();


    }
}

class A extends Thread {
    boolean fool = true;

    public void setFool(boolean fool) {
        this.fool = fool;
    }

    @Override
    public void run() {
        while (fool) {
            System.out.println((int)(Math.random()*100 +1));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class B extends Thread {
    private A a;
    private Scanner scanner = new Scanner(System.in);

    public B(A a) {
        this.a = a;
    }


    @Override
    public void run() {
        //接收到用户收入
        while (true) {
            System.out.println("请输入Q进行终止");
            char c = scanner.next().toUpperCase().charAt(0);//接收输入的字符
            if (c == 'Q') {
                a.setFool(false);
                System.out.println("B退出");
                break;
            }
        }

    }
}