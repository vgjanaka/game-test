package OurGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Dude {

    int x, dx, y, nx, nx2, left, dy;
    Image still, jump, reverse;

    int ammo = 10;

    ImageIcon s = new ImageIcon("./img/doc1.png");
    ImageIcon j = new ImageIcon("./img/docm1.png");
    ImageIcon l = new ImageIcon("./img/docm2.png");

    static ArrayList bullets;

    public Dude() {
        x = 75;
        left = 150;
        nx = 0;
        nx2 = 685;
        y = 172;
        still = s.getImage();
        bullets = new ArrayList();

    }

    public Rectangle getBounds() {
        return new Rectangle(left, y, 63, 154);
    }

    public static ArrayList getBullets() {
        return bullets;
    }

    public void fire() {
        if (ammo > 0) {
            ammo--;
		//The v is from the board class, which corresponds to the character's
            //position when it is jumping, resulting in the bullet being formed 
            //at a higher position when the character is at the peak of its jump
            Bullet z = new Bullet((left + 60), (Board.v + 154 / 2));
            bullets.add(z);
        }
    }

    public void move() {
        if (dx != -1) {
            if (left + dx <= 150) {
                left += dx;
            } else {
                x = x + dx;

                nx2 = nx2 + dx;
                nx = nx + dx;
            }
        } else {
            if (left + dx > 0) {
                left = left + dx;
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getLeft() {
        return left;
    }

    public int getnX() {
        return nx;
    }

    public int getnX2() {
        return nx2;
    }

    public int getdx() {
        return dx;
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
            dx = 1;
            still = s.getImage();
        }

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_UP) {
            dy = 1;
            still = j.getImage();
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

        if (key == KeyEvent.VK_UP) {
            dy = 0;
            still = s.getImage();
        }
    }
}
