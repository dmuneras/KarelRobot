
import java.awt.Color;
import kareltherobot.World;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dmuneras
 */
public class Main {

      public static void main(String[] args) throws InterruptedException {
        World.readWorld("/Users/dmuneras/Downloads/KJRDistribution060110/mundo.kwld");
        //World.reset();
        World.setVisible(true);
        Karelito hw1 = new Karelito(4,1, Color.BLUE);
        Karelito hw2 = new Karelito(4,2, Color.BLACK);
        Karelito hw3 = new Karelito(4,3, Color.MAGENTA);
        Karelito hw4 = new Karelito(4,4, Color.ORANGE);
        Karelito hw5 = new Karelito(4,5, Color.PINK);
        Thread t1 = new Thread(hw1);
        t1.setDaemon(false);
        Thread t2 = new Thread(hw2);
        t2.setDaemon(false);
        Thread t3 = new Thread(hw3);
        t3.setDaemon(false);
        Thread t4 = new Thread(hw4);
        t4.setDaemon(false);
        Thread t5 = new Thread(hw5);
        t5.setDaemon(false);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

}
