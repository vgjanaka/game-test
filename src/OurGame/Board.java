package OurGame;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Board extends JPanel implements ActionListener, Runnable {

    Dude p;
    public Image img;
    Timer time;
    static int v = 172;
    Thread animator;
    Enemy en;
    Enemy en2;

    boolean lost = false;

    boolean a = false;
    boolean done2 = false;

    static Font font = new Font("SanSerif", Font.BOLD, 24);

    public Board() {
        p = new Dude();
        addKeyListener(new AL());
        setFocusable(true);
        ImageIcon i = new ImageIcon("./img/bak.jpg");
        img = i.getImage();
        time = new Timer(5, this);
        time.start();
        en = new Enemy(700, 200, "./img/eny.png");
        en2 = new Enemy(700, 200, "./img/eny.png");
    }

    public void actionPerformed(ActionEvent e) {
        checkCollisions();
        ArrayList bullets = Dude.getBullets();
        for (int w = 0; w < bullets.size(); w++) {
            Bullet m = (Bullet) bullets.get(w); 
            if (m.getVisible() == true) {
                m.move();
            } else {
                bullets.remove(w);
            }
        }

        p.move();

        if (p.x > 400) {
            en.move(p.getdx(), p.getLeft());
        }
        if (p.x > 500) {
            en2.move(p.getdx(), p.getLeft());
        }
        repaint();
    }

    public void checkCollisions() {
        Rectangle r1 = en.getBounds();
        Rectangle r2 = en2.getBounds();
        ArrayList bullets = Dude.getBullets();
        for (int w = 0; w < bullets.size(); w++) {
            Bullet m = (Bullet) bullets.get(w);
            Rectangle m1 = m.getBounds();
            if (r1.intersects(m1) && en.Alive()) {
                en.isAlive = false;
                m.visible = false;
            } else if (r2.intersects(m1) && en2.Alive()) {
                en2.isAlive = false;
                m.visible = false;
            }
        }

        Rectangle d = p.getBounds();
        if (d.intersects(r1) || d.intersects(r2)) {
            lost = true;
        }

    }

    public void paint(Graphics g) {
        if (lost) {
            System.out.println("YOU SUCK");
        }
        if (p.dy == 1 && done2 == false) {
            done2 = true;
            animator = new Thread(this);
            animator.start();
        }

        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        if ((p.getX() - 590) % 2400 == 0)// p.getX() == 590 || p.getX() == 2990)...
        {
            p.nx = 0;
        }
        if ((p.getX() - 1790) % 2400 == 0)// p.getX() == 1790 || p.getX() == 4190)...
        {
            p.nx2 = 0;
        }

        g2d.drawImage(img, 685 - p.getnX2(), 0, null);
        if (p.getX() > 590) {
            g2d.drawImage(img, 685 - p.getnX(), 0, null);
        }
        g2d.drawImage(p.getImage(), p.left, v, null);

        if (p.getdx() == -1) {
            g2d.drawImage(img, 685 - p.getnX2(), 0, null);
            g2d.drawImage(p.getImage(), p.left, v, null);
        }

        ArrayList bullets = Dude.getBullets();
        for (int w = 0; w < bullets.size(); w++) {
            Bullet m = (Bullet) bullets.get(w);
            if (m.getVisible()) {
                g2d.drawImage(m.getImage(), m.getX(), m.getY(), null);
            }
        }
        g2d.setFont(font);
        g2d.setColor(Color.BLUE);
        g2d.drawString("Ammo left: " + p.ammo, 500, 20);
        if (p.x > 400) {
            if (en.Alive() == true) {
                g2d.drawImage(en.getImage(), en.getX(), en.getY(), null);
            }
        }
        if (p.x > 500) {
            if (en2.Alive() == true) {
                g2d.drawImage(en2.getImage(), en2.getX(), en2.getY(), null);
            }
        }
    }

    private class AL extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            p.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            p.keyPressed(e);
        }
    }

    boolean h = false;
    boolean done = false;

    public void cycle() {
        if (h == false) {
            v--;
        }
        if (v == 150) {
            h = true;
        }
        if (h == true && v <= 172) {
            v++;
            if (v == 172) {
                done = true;
            }
        }
    }

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
        done2 = false;
    }

}
