/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Janaka Pathma Kumara
 */
public class Enemy {
    private Image img;
    private int x;
    private int y;
    private boolean isAlive = true;

    public Enemy(String location, int startx, int starty) {
        
        this.x = startx;
        this.y = starty;
        
        ImageIcon i = new ImageIcon(location);
        img = i.getImage();
    }

    /**
     * @return the img
     */
    public Image getImg() {
        return img;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the isAlive
     */
    public boolean IsAlive() {
        return isAlive;
    }
    
    public void move(int dx,int left){
       if (dx == 1 && !((left + dx) < 150))//Added this to only move enemy when character moves forward (not back)
        {
            x = x - dx;
        }
    }
}
