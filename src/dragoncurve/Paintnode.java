/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dragoncurve;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author frick
 */
public class Paintnode {
    
    private Queue<QueueItem> path;
    
    private Direction direction;
    
    private int lowX;
    private int lowY;
    
    private int highX;
    private int highY;
    
    private int currX;
    private int currY;
    
    private int originX;
    private int originY;
    
    private int maxDist;
    
    public Paintnode(){}
    
    // ~~~~~ Fractal Developments ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void beginPaint(int steps, int beginX, int beginY){
        path = new ConcurrentLinkedQueue<>();
        
        lowX = beginX;
        highX = beginX;
        currX = beginX;
        originX = beginX;
        
        lowY = beginY;
        highY = beginY;
        currY = beginY;
        originY = beginY;
        
        this.maxDist = 0;
        
        direction = Direction.NORTH;
        
        //F
        drawForward();
        //A
        A(steps);
        //+
        turnRight(); 
        
        //F
        drawForward();
        //A
        A(steps);
        //+
        turnRight(); 
        
        System.out.println(this.lowX + " " + this.lowY);
        System.out.println(this.highX + " " + this.highY);
    }
    
    private void A (int step) {
        //A ↦ A+BF+      
        if(step > 0){
            //A
            A(step - 1);  
            //+
            turnRight();           
            //Y
            B(step - 1);
            //F
            drawForward();
            //+
            turnRight();
        }   
    }
    
    private void B (int step) {
        //B → −FX−B
        if(step > 0){
            //-
            turnLeft();
            //F
            drawForward();
            //A
            A(step - 1);           
            //-
            turnLeft();
            //B
            B(step - 1);
        }
    }
    
    //F
    private void drawForward() {
        int dist;
        
        this.currX = this.currX + this.direction.xVal;
        this.currY = this.currY + this.direction.yVal;
        
        dist = this.currDist();
        
        this.path.add( new QueueItem(dist , this.currX, this.currY) );
        
        if(dist > this.maxDist)
            this.maxDist = dist;
        
        if(this.currX < this.lowX)
            this.lowX = this.currX;
        if(this.currX > this.highX)
            this.highX = this.currX;
        
        if(this.currY < this.lowY)
            this.lowY = this.currY;
        if(this.currY > this.highY)
            this.highY = this.currY;
    }
    
    //-
    private void turnLeft(){
        this.direction = this.direction.turnLeft();      
    }
    
    //+
    private void turnRight(){
        this.direction = this.direction.turnRight();
    }
    
    public int currDist(){
        return (int) Math.sqrt( 
                Math.pow( currX - originX, 2 ) + Math.pow( currY - originY, 2 ) 
        );
    }
    
    // ~~~~~ Canvas Operations ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public int[][] toCanvas(){
        int additiveX = Math.abs(lowX) + 1;
        int additiveY = Math.abs(lowY) + 1;
        
        int[][] canvas = new int[highX + additiveX + 2][highY + additiveY + 2];
        
        while( !this.path.isEmpty() ){
            QueueItem curr = this.path.remove();
            canvas[curr.getX() + additiveX][curr.getY() + additiveY] = curr.getValue();
        }
        
        return canvas;
    }

    public int getMaxDist() {
        return maxDist;
    }       
}
