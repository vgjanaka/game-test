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
public class Bullet {
    int x,y;
    Image img;
    boolean visible ;

    public Bullet(int startx, int starty) {
        this.x = startx;
        this.y = starty;
        ImageIcon newBullet = new ImageIcon("./img/bullt.png");
        img = newBullet.getImage();
        visible =true;
    
    }
    
    
    public int getY(){
        return y;
    }
    
    public int getX(){
        return x;
    }
    
    public boolean getVisible(){
        return visible;
    }
    
     public Image getImg(){
        return img;
    }
    public void move(){
        x = x+2;
        if(x>700){
            visible = false;
        }
    }
    
}
