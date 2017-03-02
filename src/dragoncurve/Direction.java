/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dragoncurve;

/**
 *
 * @author frick
 */
public enum Direction {
   NORTH (0, 1),
   EAST (1, 0),
   SOUTH (0, -1),
   WEST (-1, 0),
   ERR (-5, 5);
   
   public final int xVal;
   public final int yVal;
   
    Direction(int xVal, int yVal){
       this.xVal = xVal;
       this.yVal = yVal;
    }
   
    public Direction turnRight(){
        Direction ret;
        
        switch(this){
            case NORTH:
                ret = EAST;
                break;
            case EAST:
                ret = SOUTH;
                break;
            case SOUTH:
                ret = WEST;
                break;
            case WEST:
                ret = NORTH;
                break;
            default:
                ret = ERR;
                break;
        }
        
        return ret;
    }
    
    public Direction turnLeft(){
        Direction ret;
        
        switch(this){
            case NORTH:
                ret = WEST;
                break;
            case EAST:
                ret = NORTH;
                break;
            case SOUTH:
                ret = EAST;
                break;
            case WEST:
                ret = SOUTH;
                break;
            default:
                ret = ERR;
                break;
        }
        
        return ret;
    }
   
}
