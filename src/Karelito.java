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
    public static final int NORTH = 1;
    public static final int SOUTH = 2;
    public static final int WEST =2;
    public static final int EAST =4;

    private int x;
    private int y;
    private int current_x;
    private int current_y;
    private Color color;
    private int state;
    private int mermaid_x;
    private int mermaid_y;
    private int last_move;


    public Karelito(int x, int y, Color color){
        super(x,y,North,10,color);
        this.x = x;
        this.y = y;
        this.current_x = x;
        this.current_y = y;
        state = SEARCHING;
    }



    public void turnN(int n){
        for(int i=0; i < n; i++){
            turnLeft();
        }
    }

    public void actCoord(){
        if(facingNorth()){
            current_x += 1;
            
        }else if(facingEast()){
            current_y +=1;
       
        }else if(facingWest()){
            current_y -= 1;
        }else{
            current_x -= 1;
            
        }
        System.out.println("current_x:" + current_x + "current_y:" + current_y);

    }

    public void moveNorth(){
        while (!facingNorth()){
            turnLeft();
        }
    }

    public void moveFront(){
        while(frontIsClear()){
            move();
            actCoord();
        }


    }
    public void findMermaids(){
        moveFront();
        last_move = NORTH;
        turnN(3);
        if(frontIsClear()){
            moveFront();
            last_move = EAST;
        }
        turnN(2);
        if(frontIsClear()){
            moveFront();
            last_move = WEST;
        }
        turnN(1);
    }

    public void findMaze(){
        if(frontIsClear()){
            move();
            actCoord();
            moveNorth();
        } else {
            turnN(3);
            if(frontIsClear()){
                move();
                actCoord();
                moveNorth();
            }else{

                turnN(2);
                if(frontIsClear()){
                    move();
                    actCoord();
                    moveNorth();
                } else{
                    turnN(1);
                    actCoord();
                    moveNorth();
                }
            }
        }

    }

    @Override
    public void run(){

            while (true){
                switch(state){
                    case SEARCHING:
                        //Hacer que vaya al punto de las sirenas
                        findMaze();
//                        if(this.nextToABeeper()){
//                            this.pickBeeper();
//                            state = FETCHING;
//                        }else{
//                            state = RETURNING;
//                        }
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
