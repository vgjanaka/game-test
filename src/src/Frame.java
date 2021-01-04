/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import javax.swing.JFrame;

/**
 *
 * @author Janaka Pathma Kumara
 */
public class Frame {

    public Frame() {
        JFrame frame = new JFrame("2D Game");
        frame.add(new Bord());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 365);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    
    
    public static void main(String[] args) {
        new Frame();
    }
}
