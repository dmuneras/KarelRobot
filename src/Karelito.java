/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import kareltherobot.*;

class Karelito extends Robot implements Runnable
{

    public static final int SEARCHING = 1;
    public static final int FETCHING = 2;
    public static final int RETURNING = 3;

    private int x;
    private int y;
    private int current_x;
    private int current_y;
    private Color color;
    private int state;
    private int mermaid_x;
    private int mermaid_y;


    public Karelito(int x, int y, Color color){
        super(x,y,North,10,color);
        this.x = x;
        this.y = y;
        this.current_x = x;
        this.current_y = y;
        state = SEARCHING;
    }



    public void turnRight(){
        this.turnLeft();
        this.turnLeft();
        this.turnLeft();
    }




    public void run(){

            while (true){
                switch(state){
                    case SEARCHING:
                        //Hacer que vaya al punto de las sirenas
                        this.turnRight();
                        if(this.nextToABeeper()){
                            this.pickBeeper();
                            state = FETCHING;
                        }else{
                            state = RETURNING;
                        }
                        break;
                    case FETCHING:
                        //Hacer que vaya al punto 0,0
                        this.putBeeper();
                        state = SEARCHING;
                        break;
                    case RETURNING:
                        //Hacer que vaya al punto x,y
                        this.turnOff();
                        break;
                }
            }
    }

 

}
