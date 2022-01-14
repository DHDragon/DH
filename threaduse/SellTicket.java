package com.tank.threaduse;

/**
 * @author D
 * @version 1.0
 */

public class SellTicket {
    public static void main(String[] args) {
        SellTicket01 sellTicket01 = new SellTicket01();
        SellTicket01 sellTicket02 = new SellTicket01();
        SellTicket01 sellTicket03 = new SellTicket01();
        sellTicket01.start();
        sellTicket02.start();
        sellTicket03.start();
//        SellTicket02 sellTicket02 = new SellTicket02();
//        new Thread(sellTicket02).start();
//        new Thread(sellTicket02).start();
//        new Thread(sellTicket02).start();

//        if ( <= 0) {
//
//            SellTicket01 sellTicket01 = new SellTicket01();
//            SellTicket01 sellTicket02 = new SellTicket01();
//            SellTicket01 sellTicket03 = new SellTicket01();
//            sellTicket01.start();
//            sellTicket02.start();
//            sellTicket03.start();
//            System.out.println(Thread.currentThread().getName() + "--------");
//            sellTicket01.setFool(false);
//            sellTicket01.setFool(false);
//            sellTicket01.setFool(false);
//        }

    }
}

//继承Thread
class SellTicket01 extends Thread {
    private static int num = 50;
    private Boolean fool = true;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setFool(Boolean fool) {
        this.fool = fool;
    }

    public synchronized void mm(){
        if (num <= 0) {
            System.out.println("售票结束");
            fool = false;
            return;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("窗口" + Thread.currentThread().getName() + "余票" + (--num));
    }

    public void run() {
        while (fool) {
            mm();
//            if (num <= 0) {
//                System.out.println("结束");
//                break;
//            }
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("窗口" + Thread.currentThread().getName() + "余票" + (--num));
        }
    }
}

//实现Runnable
class SellTicket02 implements Runnable {
    private int num = 100;
    private Boolean fool = true;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setFool(Boolean fool) {
        this.fool = fool;
    }

    public synchronized void mm(){
        if (num <= 0) {
            System.out.println("售票结束");
            fool = false;
            return;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("窗口" + Thread.currentThread().getName() + "余票" + (--num));
    }

    @Override
    public void run() {
        while (fool) {
            mm();

        }
    }
}

