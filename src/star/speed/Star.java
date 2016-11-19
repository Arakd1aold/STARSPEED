package star.speed;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.ERROR;
import java.util.Random;
import static star.speed.Game.HEIGHT;
import static star.speed.Game.WIDTH;

public class Star {

    Color starColor = Color.WHITE;
    private int x = 1;
    public int y = 1;
    
    private int sizeX = 2;
    public int sizeY = 4;  
    
    
    
    
    Random r = new Random();

    public Star(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public void render(Graphics g) {
        g.setColor(starColor);
        g.fillRect(x, y, sizeX, sizeY);
    }

    public void tick() {

        y += 8;

        if (y > 480 + 32) {
            y = 0;
            x = r.nextInt(Game.WIDTH * Game.SCALE);

        }
        
    
        
        
    }

  

    public int getY() {
        return y;
    }
    
    public void warpUp(){
        sizeY ++;
        y ++;
        
    }
    
    

}