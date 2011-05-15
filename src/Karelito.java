/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import kareltherobot.*;

class Karelito extends Robot implements Runnable
{

    public static final int SEARCHING_WALL = 1;
    public static final int FETCHING = 2;
    public static final int RETURNING = 3;
    public static final int NORTH = 1;
    public static final int SOUTH = 2;
    public static final int WEST =2;
    public static final int EAST =4;

    private int x;
    private int y;
    private int currentX;
    private int currentY;
    private int state;

    public Karelito(int x, int y, Color color){
        super(x,y,North,0,color);
        this.x = x;
        this.y = y;
        this.currentX = x;
        this.currentY = y;
        state = SEARCHING_WALL;
    }

    public void turnN(int n){
        for(int i=0; i < n; i++){
            turnLeft();
        }
    }

    public void pickNBeepers(int n){
        for(int i=0; i < n; i++){
            pickBeeper();
        }
    }

    public void putNBeepers(int n){
        for(int i=0; i < n; i++){
            putBeeper();
        }
    }

    public void actCoord(){
        if (facingNorth()){
            currentX += 1;
        }else if (facingEast()){
            currentY += 1;
        }else if (facingWest()){
            currentY -= 1;
        }else if (facingSouth()){
            currentX -= 1;
        }
    }

    public boolean stickToTheWall(){
        turnLeft();
        boolean wall = !frontIsClear();
        turnN(3);
        return wall;
    }

    public void moveFront(){
        while(frontIsClear()){
            move();
            actCoord();
        }
    }

    public void findMermaids(){
        boolean finished = false;
        while(!finished){
            if (nextToABeeper() && !anyBeepersInBeeperBag()){
                pickNBeepers(10);
            }
            if (frontIsClear() && stickToTheWall()){
                move();
                actCoord();
            }
            if (frontIsClear() && !stickToTheWall()){
                turnN(1);
                move();
                actCoord();
            }
            if (!frontIsClear() && stickToTheWall()){
                turnN(3);
            }
            if (!frontIsClear() && !stickToTheWall()){
                turnN(1);
                move();
                actCoord();
            }
            if ((currentX == 1 && currentY == 1) && anyBeepersInBeeperBag()){
                putNBeepers(10);
                move();
                actCoord();
            }
            if ((currentX == 1 && currentY == 1) && !anyBeepersInBeeperBag()){
                finished = true;
            }
        }
    }

    public void goHome(){
        while(currentX != x){
            if (currentX < x){
                while(!facingNorth()){
                    turnLeft();
                }
                move();
                actCoord();
            }else{
                while(!facingSouth()){
                    turnLeft();
                }
                move();
                actCoord();
            }
        }
        while(currentY != y){
            if (currentY < y){
                while(!facingEast()){
                    turnLeft();
                }
                move();
                actCoord();
            }else{
                while(!facingWest()){
                    turnLeft();
                }
                move();
                actCoord();
            }
        }
    }

    @Override
    public void run(){

            while (true){
                switch(state){
                    case SEARCHING_WALL:
                        moveFront();
                        turnN(3);
                        state = FETCHING;
                        break;
                    case FETCHING:
                        findMermaids();
                        state = RETURNING;
                        break;
                    case RETURNING:
                        goHome();
                        while(!facingNorth()){
                            turnLeft();
                        }
                        this.turnOff();
                        break;
                }
            }
    }
}