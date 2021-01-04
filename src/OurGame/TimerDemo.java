/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OurGame;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {
  Toolkit toolkit;

  Timer timer;

  public TimerDemo(int seconds) {
    toolkit = Toolkit.getDefaultToolkit();
    timer = new Timer();
    timer.schedule(new RemindTask(), seconds * 1000);
  }

  class RemindTask extends TimerTask {
    public void run() {
      System.out.println("Time's up!");
      toolkit.beep();
      System.exit(0); 
    }
  }

  public static void main(String args[]) {
    System.out.println("About to schedule task.");
    new TimerDemo(5);
    System.out.println("Task scheduled.");
  }
}  