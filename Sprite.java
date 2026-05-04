/**
 * Final Project Assignment: Sprite Class
 * @author Katelyn and Amanda
 * @version Dec 2022
 */

import java.awt.Graphics;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite{
    private int x;
    private int y;
    private BufferedImage picture;
    private int width;
    private int height;
    
// Contructors --------------------------------------------------------------------------------------------------------
    //Images with no movement
    Sprite(int x, int y, String picName){
       this.x = x;
        this.y = y;
        try {                
            this.picture = ImageIO.read(new File(picName));
        } 
        catch (IOException ex){System.out.println("File not found!");} 
        this.width = this.picture.getWidth();
        this.height = this.picture.getHeight();
    }
    
//Setters and Getters --------------------------------------------------------------------------------------------------
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getWidth(){
        return this.width;
    }    
    public int getHeight(){
        return this.height;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    
    
//Draws Images -------------------------------------------------------------------------------------------------------
    public void draw(Graphics g){
        g.drawImage(this.picture, this.x, this.y, null);
    }
    
//Class Methods ------------------------------------------------------------------------------------------------------

   
//Last bracket
}