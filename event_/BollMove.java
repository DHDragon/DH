package com.tank.event_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author D
 * @version 1.0
 */

public class BollMove extends JFrame{
    private MyPanl mp = null;
    public static void main(String[] args){
        BollMove bollMove = new BollMove();

    }

    public BollMove() {
        mp = new MyPanl();
        this.add(mp);
        this.setSize(500,500);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}



class MyPanl extends JPanel implements KeyListener {
    private int x ;
    private int y ;

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(getX() + 10,getY() + 10,20,20);
//        g.drawOval(10,10,100,100);
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    //按键被按下的监听，会触发该方法
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_DOWN){
            setY(getY()+1);
        }else if (e.getKeyCode() == e.VK_UP){
            setY(getY()-1);
        }else if (e.getKeyCode() == e.VK_LEFT){
            setX(getX()-1);
        }else if (e.getKeyCode() == e.VK_RIGHT){
            setX(getX()+1);
        }
        this.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

