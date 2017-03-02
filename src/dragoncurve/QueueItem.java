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
public class QueueItem {
    //At what value (level) did we take this action?
    private int value;
    
    //Where are we?
    private int x;
    private int y;

    public QueueItem(int step, int x, int y) {
        this.value = step;
        this.x = x;
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
