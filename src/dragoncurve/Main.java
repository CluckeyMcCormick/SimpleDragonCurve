/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dragoncurve;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author frick
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int steps = 21;
        
        Paintnode nodey = new Paintnode();
        nodey.beginPaint(steps, 0, 0);
        toPicture( nodey.toCanvas(), nodey.getMaxDist() );
    }    
    
    private static void toPicture(int[][] canvas, int maxValue){
        BufferedImage bi;
        Color[] colors;
        
        int colorStep;
        
        colors = new Color[16];
        
        colors[0] = new Color(75, 91, 114);
        colors[1] = new Color(0, 106, 255);
        colors[2] = new Color(68, 184, 57);
        colors[3] = new Color(114,156, 251);
        colors[4] = new Color(212, 54, 85);
        colors[5] = new Color(176, 50, 255);
        colors[6] = new Color(217, 118, 65);
        colors[7] = new Color(170, 196, 178);
        colors[8] = new Color(128, 151, 156);
        colors[9] = new Color(48, 165, 255);
        colors[10] = new Color(144, 255, 79);
        colors[11] = new Color(168, 212, 255);
        colors[12] = new Color(255, 82, 82);
        colors[13] = new Color(255, 107, 255);
        colors[14] = new Color(255, 255, 102);
        colors[15] = new Color(255, 250, 232);
        
        colorStep = maxValue / 15;
        
        bi = new BufferedImage( 
            canvas.length, canvas[0].length, BufferedImage.TYPE_INT_RGB
        );
        
        for(int i = 0; i < canvas.length; i++)
            for(int j = 0; j < canvas[i].length; j++)
                bi.setRGB(
                        i, j, colors[resolveColors( canvas[i][j], colorStep )].getRGB() 
                );
        
        try {
            // retrieve image
            File outputfile = new File("saved.png");
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            System.out.println("Extreme error: oops.");
        }
    }

    private static int resolveColors(int value, int colorStep) {
        if(value == 0)
            return 0;
        else if( value / colorStep == 0)
            return 1;
        else    
            return (value / colorStep);
    }
    
}
