/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Janaka Pathma Kumara
 */
public class Bord extends JPanel implements ActionListener, Runnable {

    Dude p;
    Image img;
    Timer time;
    Thread animator;
    int v = 172;
    
    Enemy en;
    Enemy en2;
    static  Font font = new Font("sanSerif", Font.BOLD, 24);
    public Bord() {
        p = new Dude();
        addKeyListener(new AL());
        setFocusable(true);
        ImageIcon i = new ImageIcon("./img/bak.jpg");
        img = i.getImage();
        time = new Timer(5, this);
        time.start();
        en = new Enemy("./img/eny.png",400,250);
        en2 = new Enemy("./img/eny.png",500,250);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList bullets = Dude.getbullets();
        for (int i = 0; i < bullets.size(); i++) {
            Bullet m = (Bullet) bullets.get(i);
            if (m.getVisible() == true) {
                m.move();
            }else{
                bullets.remove(i);
            }

        }

        p.move();
        
        if(p.x>400){
            en.move(p.getx(),p.left);
        }
        if(p.x>500){
            en2.move(p.getx(),p.left);
        }
        
        repaint();
    }

    boolean k = false;

    @Override
    public void paint(Graphics g) {
        if (p.dy == 1 && k == false) {
            k = true;
            animator = new Thread(this);
            animator.start();
        }

        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if ((p.getx() - 590) % 2400 == 0) {
            p.nx = 0;
        }
        if ((p.getx() - 1790) % 2400 == 0) {
            p.nx2 = 0;
        }
        g2d.drawImage(img, 685 - p.nx2, 0, null);
        if (p.getx() >= 590) {
            g2d.drawImage(img, 685 - p.nx, 0, null);
        }
        g2d.drawImage(p.getImage(), p.left, v, null);

        ArrayList bullets = Dude.getbullets();
        for (int i = 0; i < bullets.size(); i++) {
            Bullet m = (Bullet) bullets.get(i);
            g2d.drawImage(m.getImg(), m.getX(), m.getY(), null);

        }
        
        g2d.setFont(font);
        g2d.setColor(Color.BLUE);
        g2d.drawString("Ammo Left " +p.ammo, 500, 30);
        
       if(p.x>400){
            if(en.IsAlive()==true){
                g2d.drawImage(en.getImg(), en.getX(), en.getY(), null);
            }
            
        }
        
        if(p.x>500){
            if(en2.IsAlive()==true){
                g2d.drawImage(en2.getImg(), en2.getX(), en2.getY(), null);
            }
            
        }
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (done == false) {

            cycle();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = 10 - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
            }

            beforeTime = System.currentTimeMillis();
        }
        done = false;
        h = false;
        k = false;
    }
    boolean h = false;
    boolean done = false;

    public void cycle() {
        if (h == false) {
            v--;
        }

        if (v == 125) {
            h = true;
        }

        if (h == true && v <= 172) {
            v++;
            if (v == 172) {
                done = true;
            }

        }
    }

    private class AL extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            p.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            p.keyPressed(e);
        }
    }
}
