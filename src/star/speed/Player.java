package star.speed;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject implements EntityA {

  

    private double velX = 0;
    private double velY = 0;

    private Game game;
    
    private Textures tex;

    public Player(double x, double y, Textures tex) { //CONSTRUCTOR ITS A CREATE EVENT FOR THE PLAYER CLASS JUST BY INIT THE PLAYER CLASS THIS IS CALLED
        super(x,y);
        this.tex = tex;
        this.game = game;

       
       

    }

    public void tick() {
        x += velX;
        y += velY;

        if (x <= 0) {//KEEP THE PLAYER IN THE BOUNDS
            x = 0;
        }
        if (x >= 640 -20) {
            x = 640 - 20;
        }
        if (y < 480 / 2) {
            y = 480 / 2;
        }
        if (y > 480 -32) {
            y = 480 -32;
       
    
        }
        
         if (y < game.HEIGHT * game.SCALE - 32)  {
           y = y += 3;
       
    
        }
    }

    public void render(Graphics g) {
        g.drawImage(tex.player, (int) x, (int) y, null); //(INT) CASTS TO INT FROM THE ORIGNAL FLOATS

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVelX(double velX) {

        this.velX = velX;
    }

    public void setVelY(double velY) {

        this.velY = velY;
    }
    
            public Rectangle getBounds(int width, int height){
            return new Rectangle ((int) x, (int)y, width, height);

}
            

  

}
