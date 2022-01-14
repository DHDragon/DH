package com.tank.threaduse;

/**
 * @author D
 * @version 1.0
 */

public class HomeWork01 {
    public static void main(String[] args){
        Qq qq = new Qq();
        Thread thread = new Thread(qq);
        thread.setName("线程1");
        Thread thread2 = new Thread(qq);
        thread2.setName("线程2");
        thread.start();
        thread2.start();
    }
}

//取款线程
//设计多个线程共享资源，使用Runnable
class Qq implements Runnable{
    private double money = 10000;
    private boolean fool = true;
//    private Scanner scanner = new Scanner(System.in);

    public boolean isFool() {
        return fool;
    }
    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    @Override
    public void run() {
        while (fool){
            //使用synchronized实现线程同步
            //当多线程执行到这里，会去争夺this对象锁，
            // 谁拿到谁执行synchronized代码块，执行结束后释放this对象锁
            //没有拿到的则阻塞blocked，准备继续争夺
            synchronized (this){
                if (money <= 1000){
                    System.out.println("金额不足");
                    break;
                }
//            System.out.println("请输入金额");
//            int i = scanner.nextInt();
//            if (getMoney()-i > 0){
//                setMoney(getMoney()-i);
//            }
                money -= 1000;
                System.out.println(Thread.currentThread().getName() + "余额" +getMoney());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
