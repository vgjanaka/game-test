/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Janaka Pathma Kumara
 */
public class Dude {

    int x, dx, y, nx2, nx, left,dy;
    Image still;
    int ammo = 10;
    
    ImageIcon i = new ImageIcon("./img/doc1.png");
    ImageIcon l = new ImageIcon("./img/docm2.png");
    ImageIcon j = new ImageIcon("./img/docm1.png");

    static  ArrayList<Bullet> bullet;
    public Dude() {

        still = i.getImage();
        left = 150;
        x = 75;
        nx2 = 685;
        nx = 0;
        y = 172;
        bullet = new ArrayList();
    }

    public static ArrayList getbullets(){
        return bullet;
    }
    
    public void fire(){
        
        if(ammo>0){
            ammo--;
            Bullet z = new Bullet((left+60), (y+154)/2);
        bullet.add(z);
        }
        
    }
    
    
    public void move() {
        if (dx != -1) {
            if (left + dx <= 150) {
                left = left + dx;
            } else {
                x = x + dx;
                
                nx2 = nx2 + dx;
                nx = nx + dx;
            }

        }else{
            if(left+dx >0){
                left=left+dx;
            }
        }

    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    public Image getImage() {
        return still;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
            still = l.getImage();
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
            still = i.getImage();
        }
        if(key == KeyEvent.VK_UP){
            dy = 1;
            still =j.getImage();
        }
         if(key == KeyEvent.VK_SPACE){
            fire();
        }
        
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        if(key == KeyEvent.VK_UP){
            dy = 0;
            still = i.getImage();
        }
    }
}
