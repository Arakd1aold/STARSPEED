
package star.speed;

import java.awt.Color;
import java.awt.Graphics;


public class Particle {
    
   private int x;
   private int y;
   private int dx;
   private int dy;
   private int size;
   private int life;
   private Color color;
   
   public Particle(int x, int y, int dx, int dy, int size, int life, Color c){
      this.x = x;
      this.y = y;
      this.dx = dx;
      this.dy = dy;
      this.size = size;
      this.life = life;
      this.color = c;
   }

public boolean tick(){
      x += dx;
      y += dy;
      life--;
      if(life <= 0){
          
      
         return true;
}
      return false;
   }

   public void render(Graphics g){
       
       
      g.setColor(color);
      g.fillRect(x-(size/2), y-(size/2), size, size);

   }
   
   public int getY(){
       return y;
   }

}
